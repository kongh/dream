define(function(require, exports, module) {
	var $ = require('jquery');
	var factory = require('treetable-curd-factory');
	
	$(function() {
		
		var treeTableConfig = { 
				expandable: true,
				loadBranches:true,
				persist: true,
				persistStoreName: "sm-dictionary-item-persist-store"
		};
        
		factory.create({
			module:'sm-dictionary-item',
			treeTableConfig:treeTableConfig
		});
		
		//more
		bindDictionaryButtonEvent();
    });
	
	function bindDictionaryButtonEvent(){
		$(".btn-toolbar button.dictionary").on("click",function(){
			var url = ctx + '/sm/dictionary';
			$('#content-frame', window.top.document).attr("src",url);
		});
	}
});