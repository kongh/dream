define(function(require, exports, module) {
	var $ = require('jquery');
	
	$(function() {
		var is = $('#login-form .box-body input').on('focus',function(){
			var $alert = $('#login-form .box-footer .alert');
			if($alert.hasClass('show')){
				$alert.removeClass('show');
			}
			if(!$alert.hasClass('hidden')){
				$alert.addClass('hidden');
			}
		});
    });
});