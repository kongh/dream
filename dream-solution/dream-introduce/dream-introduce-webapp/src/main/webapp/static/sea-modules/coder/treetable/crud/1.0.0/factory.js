
define(function(require, exports, module) {
	var $ = require('jquery');
	require('treetable')($);
	require('treetable-ajax-persist')($);
	require('jquery-form')($);
	require("jquery-select2")($);
	var util = require('app-util');
	
	//css
	seajs.use("jquery/treetable/3.2.0/css/jquery.treetable.css");
	seajs.use("jquery/treetable/3.2.0/css/jquery.treetable.theme.dream.css");
	seajs.use('jquery-select2-css');
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
		 *  treeTableConfig:treeTableConfig,[必须]
		 *  
		 * }
		 */
		factory.create = function(config){
			initTreeTable(config);
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
				 $filed.val(fillValue);
			 }      
		 }
		
		factory.doAjax = doAjax;
		//end
		
		//私有方法
		function initTreeTable(config){
			var module = config.module;
			$('#'+module+'-table').agikiTreeTable(config.treeTableConfig);
		}
		
		function highlightRow(config){
			var module = config.module;
		     $('#'+module+'-table tbody').on("mousedown", "tr", function() {
			      $(".selected").not(this).removeClass("selected");
			      $(this).toggleClass("selected");
			 });
		}
		
		function doAjax(context){
			$.ajax({
	            type: "POST",
	            dataType: "json",
	            url: context.url,
	            data: context.data,
	            success: function(result){
	            	if(result.success){
	            		context.result = result;
	            		context.successCallback(context);
	            	}else{
	            		util.showPopover(result.msg);
	            	}
	            }
	            
			});
		}
		
		//begin新增操作流程
		function bindAddButtonEvent(config){
			var module = config.module;
			$(".btn-toolbar button.add").on("click",function(){
				 var selectedRows = $('#'+module+'-table').find('tr.selected');
				 if(selectedRows.length == 1){
					 $('#addModal').modal('show');
				 }else{
					 util.showPopover("请选中一行!");
				 }
			});
		}
		
		function bindAddModalCreateButtonEvent(config){
			var module = config.module;
			var cfgCreateUrl = config.createUrl;
			$('#addModal .modal-footer button.create').on("click",function(){
				//适配data
				 var selectedRows = $('#'+module+'-table').find('tr.selected');
				 var selectedRow = selectedRows[0];
				 var parentId = $(selectedRow).data('ttId');
				//适配url
				var url = '';
				if(cfgCreateUrl){
					url = cfgCreateUrl;
				}else{
					var moduleUrl = module.replace(/-/g,'/');
					url = ctx +'/'+ moduleUrl+'/create';
				}
				url += '?parentId=';
				url += parentId;
				$('#'+module+'-create-form').ajaxSubmit({
					url:url,
				    type:'POST',
				    dataType:'json',
				    beforeSubmit:function(values,form,options){
				    },
				    success:function(result){
				    	if(result.success){
				    		$('#addModal').modal('hide');
					    	//适配context
							var context = {};
							context.config = config;
							context.refreshId = parentId;
							onRefresh(context);
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
				 var id = $(selectedRow).data('ttId');
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
				 //适配context
				 var context = {};
				 context.config = config;
				 context.url = url;
				 context.data = data;
				 context.successCallback = onGetSuccess;
				 
				 doAjax(context);
			 }else{
				 util.showPopover('请选择一行');
			 }
		}
		
		function onGetSuccess(context){
			var config = context.config;
			var data = context.result.data;
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
				//适配data
				 var selectedRows = $('#'+module+'-table').find('tr.selected');
				 var selectedRow = selectedRows[0];
				 var parentId = $(selectedRow).data('ttParentId');
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
					    	//适配context
							var context = {};
							context.config = config;
							context.refreshId = parentId;
							onRefresh(context);
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
					 var id = $(selectedRow).data('ttId');
					 var parentId = $(selectedRow).data('ttParentId');
					 var ids = [];
					 ids.push(id);
					 var data = {
						ids:id
					 };
					
					 //适配url
					 var url = '';
					 if(cfgDeleteUrl){
						 url = cfgDeleteUrl;
					 }else{
						 var moduleUrl = module.replace(/-/g,'/');
						 var url = ctx +'/'+ moduleUrl+'/deleteNode';
					 }
					 
					 //适配context
					 var context = {};
					 context.config = config;
					 context.url = url;
					 context.data = data;
					 context.refreshId = parentId;
					 context.successCallback = onDeleteSuccess;
					 
					 doAjax(context);
				 }else{
					 util.showPopover("请选中一行!");
				 }
				
			  });
		}
		
		function onDeleteSuccess(context){
			onRefresh(context);
		}
		//end删除操作流程
		
		//begin刷新操作流程
		function bindRefreshButtonEvent(config){
			 var module = config.module; 
			 $(".btn-toolbar button.refresh").on("click",function(){
				 var selectedRows = $('#'+module+'-table').find('tr.selected');
				 if(selectedRows.length == 1){
					 var selectedRow = selectedRows[0];
					 var selectedRowId = $(selectedRow).data('ttId');
					 
					 //适配context
					 var context = {};
					 context.config = config;
					 context.refreshId = selectedRowId;
					 onRefresh(context);
				 }else{
					 util.showPopover("请选中一行!");
				 }
			 });
		}
		
		/**
		 * @param context object
		 * {
		 * 	 config:config,[必须][factory context]
		 *   refreshId:refreshId,[必须][刷新节点的id]
		 * }
		 */
		function onRefresh(context){
			var config = context.config;
			var refreshId = context.refreshId;
			var module = config.module;
			$('#'+module+'-table').treetable('collapseNode',refreshId);
	    	$('#'+module+'-table').treetable('expandNode',refreshId);
		}
		//end刷新操作流程
		
		return factory;
	}());
})
