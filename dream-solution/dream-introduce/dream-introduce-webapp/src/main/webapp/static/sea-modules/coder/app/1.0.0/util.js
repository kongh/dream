
define(function(require, exports, module) {
	var $ = require('jquery');
	
	//factoryBean
	return (function(){
		var util = new Object(); 
		
		util.hasInit = false;
		
		util.showPopover = function(content){
			initShowPopover();
			var $pop = $('#main-popover', window.top.document);
			$pop.attr('data-content',content);
			$pop.popover('show');
		}
		
		//定义
		function initShowPopover(){
			if(util.hasInit) return ;
			
			var $pop = $('#main-popover', window.top.document);
			$pop.popover({
				animation:true,
			});
			$pop.on('shown.bs.popover', function () {
	        	setTimeout(function(){
	        		$pop.popover('hide');
	            },1200); 
	        });
		}
		
		return util;
	}());
})
