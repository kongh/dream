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
		
    });
});