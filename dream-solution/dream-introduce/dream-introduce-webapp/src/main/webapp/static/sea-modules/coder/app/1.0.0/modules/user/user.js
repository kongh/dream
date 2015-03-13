define(function(require, exports, module) {
	var $ = require('jquery');
	var factory = require('table-curd-factory');
	var select = require('table-select-factory');
	
	$(function() {
		var config = factory.createDefaultServerSideDataTableConfig();
		config.sAjaxSource = ctx + "/sm/user/page";
		config.fnDrawCallback = function ( oSettings ) {
        	//序号列赋值
			var $table = $('#sm-user-table');
        	factory.datatableCounter(oSettings,$table);
        };
        config.aoColumns = [{"mData":"rowNum","sWidth": "5%" },//占位
                            {"mData":"id",bVisible:false},
                            { "mData": "account" },
                            { "mData": "name" },
                            { "mData": "phone" },
                            {"mData": "email"}];
        
		factory.create({
			module:'sm-user',
			dataTableConfig:config
		})
		
		var selectConfig = select.createDefaultSelectDataTableConfig();
		select.bindContextEvent({
			module:'sm-user',
			module2:'sm-role',
		    module3:'sm-userRole',
		    dataTableConfig:selectConfig
		});
    });
	
	
});