define(function(require, exports, module) {
	var $ = require('jquery');
	var factory = require('table-curd-factory');
	var util = require('app-util');
	var select = require('treetable-select-factory');
	
	$(function() {
		var config = factory.createDefaultServerSideDataTableConfig();
		config.sAjaxSource = ctx + "/sm/role/page";
		config.fnDrawCallback = function ( oSettings ) {
        	//序号列赋值
			var $table = $('#sm-role-table');
        	factory.datatableCounter(oSettings,$table);
        };
        config.aoColumns = [{"mData":"rowNum","sWidth": "5%" },//占位
                            {"mData":"id",bVisible:false},
                            { "mData": "name"},
                            { "mData": "code"},
                            { "mData": "comments"}];
        
		factory.create({
			module:'sm-role',
			dataTableConfig:config
		});
		
		var selectConfig = {
				module:'sm-role',
				module2:'sm-resource',
			    module3:'sm-roleResource'
		};
		select.bindContextEvent(selectConfig);
    });
});