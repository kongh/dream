define(function(require, exports, module) {
	var $ = require('jquery');
	var factory = require('table-curd-factory');
	var util = require('app-util');
	
	$(function() {
		var config = factory.createDefaultServerSideDataTableConfig();
		config.sAjaxSource = ctx + "/sm/dictionary/page";
		config.fnDrawCallback = function ( oSettings ) {
        	//序号列赋值
			var $table = $('#sm-dictionary-table');
        	factory.datatableCounter(oSettings,$table);
        };
        config.aoColumns = [{"mData":"rowNum","sWidth": "5%" },//占位
                            {"mData":"id",bVisible:false},
                            { "mData": "name"},
                            { "mData": "code"},
                            { "mData": "comments"}];
        
		factory.create({
			module:'sm-dictionary',
			dataTableConfig:config
		})
		
		//more
		bindDictionaryItemButtonEvent();
    });
	
	function bindDictionaryItemButtonEvent(){
		$(".btn-toolbar button.dictionary-item").on("click",function(){
			 var selectedRows = $('#sm-dictionary-table').find('tr.selected');
			 if(selectedRows.length == 1){
				 var selectedRow = selectedRows[0];
				 var table = $('#sm-dictionary-table').dataTable();
				 var tableData = table.fnGetData(selectedRow);
				 var code = tableData.code;
				 
				 var url = ctx + '/sm/dictionary/item';
				 url += '?dictionaryCode=' + code;
				 $('#content-frame', window.top.document).attr("src",url);
			 }else{
				 util.showPopover("请选中一行!");
			 }
		});
	}
});