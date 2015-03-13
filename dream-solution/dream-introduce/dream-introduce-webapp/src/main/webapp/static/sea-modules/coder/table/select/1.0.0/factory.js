
define(function(require, exports, module) {
	var $ = require('jquery');
	require('datatables')($);
	require('datatables-bootstrap')($);
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
		 *  
		 * }
		 */
		factory.bindContextEvent = function(config){
			bindRelativeOneAndTwoButtonEvent(config);
			bindSelectModalRelativeButtonEvent(config);
			bindSelectModalResetButtonEvent(config);
		}
		
		factory.createDefaultSelectDataTableConfig = function(){
			return {
		    	"bPaginate": false,
	            "bLengthChange": false,
	            "bFilter": false,
	            "bSort": false,
	            "bInfo": false,
		    };
		}
		
		factory.create = function(config){
			initDataTable(config);
			highlightRow(config);
		}
		
		factory.getSelectedRows = function(config){
			var rows = [];
			var module2 = config.module2;
			$('#'+module2+'-select-table tbody tr').each(function(){
				var $this = $(this);
				var $input = $this.find('input[name="select"]');
				if($input.attr('checked')){
					rows.push($this);
				}
			});
			return rows;
		}
		//end
		
		//私有方法
		function initDataTable(config){
			var module2 = config.module2;
			var $table = $('#'+module2+'-select-table');
			$table.dataTable(config.dataTableConfig);
			$('#'+module2+'-select-table tbody tr input[name="select"]').each(function(){
				var $input = $(this);
				$input.on('click',function(){
					if ($input.attr("checked")) {
						$input.removeAttr("checked");
                    } else {
                    	$input.attr("checked", 'checked');
                    }
				});
			});
		}
		
		function highlightRow(config){
			var module2 = config.module2;
		     $('#'+module2+'-table tbody').on("mousedown", "tr", function() {
			      $(".selected").not(this).removeClass("selected");
			      $(this).toggleClass("selected");
			 });
		}
		
		function bindRelativeOneAndTwoButtonEvent(config){
			var module = config.module;
			var module2 = config.module2;
			var cfgRelativeUrl = config.relativeUrl;
			$(".btn-toolbar button.relative").on('click',function(){
				var $table = $('#'+module+'-table');
				var selectedRows = $table.find('tr.selected');
				if(selectedRows.length == 1){
					//适配data
					var selectedRow = selectedRows[0];
					var table = $table.dataTable();
					var tableData = table.fnGetData(selectedRow);
					var id = tableData.id;
					var ms = module.split('-');
					var datakey = ms[ms.length-1];
					datakey+= "Id";
					var data = {};
					data[datakey] = id;
					
					//适配url
					var url = '';
					if(cfgRelativeUrl){
						url = cfgRelativeUrl;
					}else{
						var module2Url = module2.replace(/-/g,'/');
						url = ctx +'/'+ module2Url+'/select';
					}
					$('#selectModal .modal-body').load(url,data,function(){
						$('#selectModal').modal('show');
					});
				 }else{
					 util.showPopover('请选择一行');
				 }
			});
			$('#selectModal').on('shown.bs.modal', function () {
				factory.create(config);
			});
		}
		
		function bindSelectModalRelativeButtonEvent(config){
			var module = config.module;
			var module2 = config.module2;
			var module3 = config.module3;
			var cfgRelativeUrl = config.relativeUrl;
			$('#selectModal .modal-footer button.relative').on("click",function(){
				//适配data
				var rows = factory.getSelectedRows(config);
				var bIds = [];
				$.each(rows,function(){
					$row = this;
					var id = $row.data('tt-id');
					bIds.push(id);
				});
				var $table = $('#'+module+'-table');
				var selectedRows = $table.find('tr.selected');
				var selectedRow = selectedRows[0];
				var table = $table.dataTable();
				var tableData = table.fnGetData(selectedRow);
				var id = tableData.id;
				
				var data = {};
				//A id
				var ms = module.split('-');
				var datakey = ms[ms.length-1];
				datakey+= "Id";
				data[datakey] = id;
				//B ids
				ms = module2.split('-');
				dataKey = ms[ms.length-1];
				dataKey += "Ids";
				data[dataKey] = bIds;
				
				
				//适配url
				var url = '';
				if(cfgRelativeUrl){
					url = cfgRelativeUrl;
				}else{
					var module3Url = module3.replace(/-/g,'/');
					url = ctx +'/'+ module3Url+'/relative';
				}
				
				$.ajax({
		            type: "POST",
		            dataType: "json",
		            url: url,
		            data: $.param(data,true),
		            success: function(result){
		            	if(result.success){
		            		util.showPopover("保存成功");
		            	}else{
		            		util.showPopover(result.msg);
		            	}
		            }
				});
			});
		}
		
		function bindSelectModalResetButtonEvent(config){
			var module = config.module;
			var module2 = config.module2;
			var module3 = config.module3;
			var cfgRelativeUrl = config.relativeUrl;
			$('#selectModal .modal-footer button.reset').on("click",function(){
				var $table = $('#'+module+'-table');
				var selectedRows = $table.find('tr.selected');
				if(selectedRows.length == 1){
					//适配data
					var selectedRow = selectedRows[0];
					var table = $table.dataTable();
					var tableData = table.fnGetData(selectedRow);
					var id = tableData.id;
					var ms = module.split('-');
					var datakey = ms[ms.length-1];
					datakey+= "Id";
					var data = {};
					data[datakey] = id;
					
					//适配url
					var url = '';
					if(cfgRelativeUrl){
						url = cfgRelativeUrl;
					}else{
						var module2Url = module2.replace(/-/g,'/');
						url = ctx +'/'+ module2Url+'/select';
					}
					
					$('#selectModal .modal-body').load(url,data,function(){
						factory.create(config);
					});
				 }else{
					 util.showPopover('请选择一行');
				 }
			});
		}
		//end
		
		return factory;
	}());
})
