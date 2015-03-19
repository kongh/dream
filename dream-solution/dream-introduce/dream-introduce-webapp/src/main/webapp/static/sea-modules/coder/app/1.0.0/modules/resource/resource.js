define(function(require, exports, module) {
	var $ = require('jquery');
	var factory = require('treetable-curd-factory');
	
	$(function() {
		
		var treeTableConfig = { 
				expandable: true,
				loadBranches:true,
				persist: true,
				persistStoreName: "sm-resource-persist-store"
		};
        
		factory.create({
			module:'sm-resource',
			treeTableConfig:treeTableConfig
		});
		
		var context = {
				url: ctx + '/sm/dictionary/item/combo',
				data:{
					dictionaryCode:'sm_resource_type'
				},
				successCallback:onGetResourceTypeSuccess
		};
		
		factory.doAjax(context);
    });
	
	function onGetResourceTypeSuccess(context){
		var result = context.result;
		$('select').select2({
			data:result.data
		});
	}
	
});