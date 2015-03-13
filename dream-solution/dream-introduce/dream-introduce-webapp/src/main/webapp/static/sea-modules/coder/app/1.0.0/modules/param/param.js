define(function(require, exports, module) {
	var $ = require('jquery');
	var factory = require('table-curd-factory');
	
	$(function() {
		var config = factory.createDefaultServerSideDataTableConfig();
		config.sAjaxSource = ctx + "/sm/param/page";
		config.fnDrawCallback = function ( oSettings ) {
        	//序号列赋值
			var $table = $('#sm-param-table');
        	factory.datatableCounter(oSettings,$table);
        };
        config.aoColumns = [{"mData":"rowNum","sWidth": "5%" },//占位
                            {"mData":"id",bVisible:false},
                            { "mData": "name"},
                            { "mData": "code"},
                            { "mData": "value"},
                            { "mData": "comments"}];
        
		factory.create({
			module:'sm-param',
			dataTableConfig:config
		})
    });
	
	
});