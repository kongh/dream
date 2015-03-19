
define(function(require, exports, module) {
	var $ = require('jquery');
	require('datatables')($);
	require('datatables-bootstrap')($);
	require('jquery-form')($);
	var util = require('app-util');
	
	//css
	seajs.use("datatables-bootstrap-css");
	
	//factoryBean
	return (function(){
		var factory=new Object(); 
		/**
		 * config参数
		 * 
		 * {
		 * 	module:moduleName,[必须]
		 *  createUrl:createUrl,[可选]
		 *  getUrl:getUrl,[可选]
		 *  updateUrl:updateUrl,[可选]
		 *  deleteUrl:deleteUrl,[可选]
		 *  dataTableConfig:dataTableConfig,[必须]
		 *  
		 * }
		 */
		factory.create = function(config){
			initDateTable(config);
			highlightRow(config);
			
			bindAddButtonEvent(config);
			bindAddModalCreateButtonEvent(config);
			bindAddModalResetButtonEvent(config);
			bindAddModalHiddenEvent(config);
			
			bindUpdateButtonEvent(config);
			bindUpdateModalUpdateButtonEvent(config);
			bindUpdateModalResetButtonEvent(config);
			bindUpdateModalHiddenEvent(config);
			bindDeleteButtonEvent(config);
			bindRefreshButtonEvent(config);
		}
		
		factory.setFormValues = function($form,data,renderers){
			 for(var prop in data){ 
				 var selector = "[name='"+prop+"']";
				 var $filed = $form.find(selector);
				 var fillValue = data[prop];
				 if(renderers){
					 var renderer = renderers[prop];
					 if(renderer){
						 fillValue = renderer(fillValue);
					 }
				 }
				 if(typeof $filed.select2 === 'function' ){
					 $filed.val(fillValue).trigger("change");
				 }else{
					 $filed.val(fillValue);
				 }
			 }      
		 }
		 
		 factory.datatableCounter = function(oSettings,$table){
			var that = $table.dataTable();
			that.$('td:first-child', {"filter":"applied"}).each( function (i) {
         		var text = '<span class="badge bg-green">'+(i+1)+'</span>';
                 that.fnUpdate( text, this.parentNode, 0, false, false );
            }); 
		 }
		 
		 factory.createDefaultServerSideDataTableConfig = function(){
			 return {
		            "bPaginate": true,
		            "bLengthChange": false,
		            "bFilter": false,
		            "bSort": false,
		            "bInfo": true,
		            "bAutoWidth": false,
		            "bServerSide": true,
		            "sAjaxDataProp": "data",
		            "oLanguage": {                          //汉化   
		                "sLengthMenu": "每页显示 _MENU_ 条记录",   
		                "sZeroRecords": "没有检索到数据",   
		                "sInfo": "显示第_START_ 到 _END_条,共_TOTAL_条",   
		                "sInfoEmtpy": "没有数据",   
		                "sProcessing": "正在加载数据...",   
		                "oPaginate": {   
		                    "sFirst": "首页",   
		                    "sPrevious": "上一页",   
		                    "sNext": "下一页",   
		                    "sLast": "尾页"  
		                }   
		            }
		         }  
		 }
		//end
		
		//私有方法
		function initDateTable(config){
			var module = config.module;
			$('#'+module+'-table').dataTable(config.dataTableConfig);
		}
		
		function highlightRow(config){
			var module = config.module;
		     $('#'+module+'-table tbody').on("mousedown", "tr", function() {
			      $(".selected").not(this).removeClass("selected");
			      $(this).toggleClass("selected");
			 });
		}
		
		function doAjax(url,data,successCallback,config){
			$.ajax({
	            type: "POST",
	            dataType: "json",
	            url: url,
	            data: data,
	            success: function(result){
	            	if(result.success){
	            		successCallback(config,result.data);
	            	}else{
	            		util.showPopover(result.msg);
	            	}
	            }
			});
		}
		
		//begin新增操作流程
		function bindAddButtonEvent(config){
			$(".btn-toolbar button.add").on("click",function(){
				$('#addModal').modal('show');
			});
		}
		
		function bindAddModalCreateButtonEvent(config){
			var module = config.module;
			var cfgCreateUrl = config.createUrl;
			$('#addModal .modal-footer button.create').on("click",function(){
				//适配url
				var url = '';
				if(cfgCreateUrl){
					url = cfgCreateUrl;
				}else{
					var moduleUrl = module.replace(/-/g,'/');
					var url = ctx +'/'+ moduleUrl+'/create';
				}
				$('#'+module+'-create-form').ajaxSubmit({
					url:url,
				    type:'POST',
				    dataType:'json',
				    beforeSubmit:function(values,form,options){
				    },
				    success:function(result){
				    	if(result.success){
				    		$('#addModal').modal('hide');
					    	onRefresh(config);
				    	}else{
				    		util.showPopover(result.msg);
				    	}
				    	
				    }
				 });
			 });
		}
		
		function bindAddModalResetButtonEvent(config){
			 $("#addModal .modal-footer button.reset").on("click",function(){
				 fireCreateFormReset(config);
			 });
		}
		
		function fireCreateFormReset(config){
			var module = config.module;
			$('#'+module+'-create-form').resetForm();
		}
		
		function bindAddModalHiddenEvent(config){
			$('#addModal').on('hidden.bs.modal',function(){
				fireCreateFormReset(config);
			});
		}
		//end新增操作流程 
		
		//begin修改操作流程
		function bindUpdateButtonEvent(config){
			$(".btn-toolbar button.update").on("click",function(){
				fireGetData(config);
			 });
		}
		
		function fireGetData(config){
			var module = config.module;
			var cfgGetUrl = config.getUrl;
			var $table = $('#'+module+'-table');
			var selectedRows = $table.find('tr.selected');
			if(selectedRows.length == 1){
				 //适配data
				 var selectedRow = selectedRows[0];
				 var table = $table.dataTable();
				 var tableData = table.fnGetData(selectedRow);
				 var id = tableData.id;
				 var data = {
						 id:id
				 };
				 //适配url
				 var url = '';
				 if(cfgGetUrl){
					 url = cfgGetUrl;
				 }else{
					 var moduleUrl = module.replace(/-/g,'/');
					 var url = ctx +'/'+ moduleUrl+'/get';
				 }
				 doAjax(url,data,onGetSuccess,config);
			 }else{
				 util.showPopover('请选择一行');
			 }
		}
		
		function onGetSuccess(config,data){
			var module = config.module;
			factory.setFormValues($('#'+module+'-update-form'),data,{
    			createDate:function(value){
    				if(value == undefined || value == null || value == 'null'){
    					return "";
    				}
    				return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
    			},
    			updateDate:function(value){
    				if(value == undefined || value == null || value == 'null'){
    					return "";
    				}
    				return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
    			}
    		});
    		$('#updateModal').modal('show');
		}

		function bindUpdateModalUpdateButtonEvent(config){
			var module = config.module;
			var cfgUpdateUrl = config.updateUrl;
			$('#updateModal .modal-footer button.update').on("click",function(){
				//适配url
				var url = '';
				if (cfgUpdateUrl) {
					url = cfgUpdateUrl;
				} else {
					var moduleUrl = module.replace(/-/g,'/');
					var url = ctx + '/' + moduleUrl + '/update';
				}
				$('#'+module+'-update-form').ajaxSubmit({
					url:url,
				    type:'POST',
				    dataType:'json',
				    beforeSubmit:function(values,form,options){
				    },
				    success:function(result){
				    	if(result.success){
				    		$('#updateModal').modal('hide');
				    		onRefresh(config);
				    	}else{
				    		util.showPopover(result.msg);
				    	}
				    }
				 });
			});
		}
		
		function bindUpdateModalResetButtonEvent(config){
			$("#updateModal .modal-footer button.reset").on("click",function(){
				fireGetData(config); 
			});
		}
		
		function fireUpdateFormReset(config){
			var module = config.module;
			$('#'+module+'-update-form').resetForm();
		}
		
		function bindUpdateModalHiddenEvent(config){
			$('#updateModal').on('hidden.bs.modal',function(){
				fireUpdateFormReset(config);
			});
		}
		//end修改操作流程

		//begin删除操作流程
		function bindDeleteButtonEvent(config){
			 var module = config.module;
			 var cfgDeleteUrl = config.deleteUrl;
			 $(".btn-toolbar button.delete").on("click",function(){
				 var selectedRows = $('#'+module+'-table').find('tr.selected');
				 if(selectedRows.length == 1){
					 //适配data
					 var selectedRow = selectedRows[0];
					 var table = $('#'+module+'-table').dataTable();
					 var tableData = table.fnGetData(selectedRow);
					 var id = tableData.id;
					 var ids = [];
					 ids.push(id);
					 var data = {
							 "ids":id
					 };
					 
					//适配url
					 var url = '';
					 if(cfgDeleteUrl){
						 url = cfgDeleteUrl;
					 }else{
						 var moduleUrl = module.replace(/-/g,'/');
						 var url = ctx +'/'+ moduleUrl+'/delete';
					 }
					 doAjax(url,data,onDeleteSuccess,config);
				 }else{
					 util.showPopover("请选中一行!");
				 }
				
			  });
		}
		
		function onDeleteSuccess(config,data){
			onRefresh(config);
		}
		//end删除操作流程
		
		//begin刷新操作流程
		function bindRefreshButtonEvent(config){
			 $(".btn-toolbar button.refresh").on("click",function(){
				 onRefresh(config);
			 });
		}

		function onRefresh(config){
			var module = config.module;
			var table = $('#'+module+'-table').dataTable();
		    table.fnDraw();
		}
		//end刷新操作流程
		
		return factory;
	}());
})
