define(function(require, exports, module) {
	var left_side_width = 220; //Sidebar width in pixels
	
	var $ = require('jquery');
	require('bootstrap')($);
	require('sidebar')($);
	var util = require('app-util');
	
	$(function() {
	    "use strict";
	    //logout
	    $('.logout').on('click',function(){
	    	$.ajax({
	            type: "POST",
	            dataType: "json",
	            url: ctx+'/logout',
	            success: function(result){
	            	if(result.success){
	            		window.top.location.replace('login');
	            	}else{
	            		util.showPopover(result.msg);
	            	}
	            }
			});
	    });
	    
	    var onAjaxStatusModalHiddenFor401 = function(e){
	    	$modal.find('.modal-body p').html('');
			window.top.location.replace('login');
	    }
	    
	    var onAjaxStatusModalHiddenFor403 = function(e){
	    	$modal.find('.modal-body p').html('');
	    }
	    
	    var onHiddenModal = onAjaxStatusModalHiddenFor403;
	    	
	    var $modal = $('#ajaxStatusErrorModal', window.top.document);
	    $modal.on('show.bs.modal',function(e){
	    	$modal.unbind('hidden.bs.modal');//移除事件
	    	$modal.on('hidden.bs.modal',onHiddenModal);
	    });
		
	    //jquery
	    $.ajaxSetup({
	    	statusCode: {
            	401: function() {
            		onHiddenModal = onAjaxStatusModalHiddenFor401;
            		$modal.find('.modal-body p').html('您未登陆或会话已失效，请重新登陆!');
            		$modal.modal('show');
            	},
            	403:function(){
            		onHiddenModal = onAjaxStatusModalHiddenFor403;
            		$modal.find('.modal-body p').html('您没有权限，访问资源['+this.url+']!');
            		$modal.modal('show');
            	}
            }
	    });
	    
	    //Enable sidebar toggle
	    $("[data-toggle='offcanvas']").click(function(e) {
	        e.preventDefault();

	        //If window is small enough, enable sidebar push menu
	        if ($(window).width() <= 992) {
	            $('.row-offcanvas').toggleClass('active');
	            $('.left-side').removeClass("collapse-left");
	            $(".right-side").removeClass("strech");
	            $('.row-offcanvas').toggleClass("relative");
	        } else {
	            //Else, enable content streching
	            $('.left-side').toggleClass("collapse-left");
	            $(".right-side").toggleClass("strech");
	        }
	    });

	    //Add hover support for touch devices
	    $('.btn').bind('touchstart', function() {
	        $(this).addClass('hover');
	    }).bind('touchend', function() {
	        $(this).removeClass('hover');
	    });

	    //Activate tooltips
	    $("[data-toggle='tooltip']").tooltip();

	    /*     
	     * Add collapse and remove events to boxes
	     */
	    $("[data-widget='collapse']").click(function() {
	        //Find the box parent        
	        var box = $(this).parents(".box").first();
	        //Find the body and the footer
	        var bf = box.find(".box-body, .box-footer");
	        if (!box.hasClass("collapsed-box")) {
	            box.addClass("collapsed-box");
	            //Convert minus into plus
	            $(this).children(".fa-minus").removeClass("fa-minus").addClass("fa-plus");
	            bf.slideUp();
	        } else {
	            box.removeClass("collapsed-box");
	            //Convert plus into minus
	            $(this).children(".fa-plus").removeClass("fa-plus").addClass("fa-minus");
	            bf.slideDown();
	        }
	    });

	    /*
	     * INITIALIZE BUTTON TOGGLE
	     * ------------------------
	     */
	    $('.btn-group[data-toggle="btn-toggle"]').each(function() {
	        var group = $(this);
	        $(this).find(".btn").click(function(e) {
	            group.find(".btn.active").removeClass("active");
	            $(this).addClass("active");
	            e.preventDefault();
	        });

	    });

	    $("[data-widget='remove']").click(function() {
	        //Find the box parent        
	        var box = $(this).parents(".box").first();
	        box.slideUp();
	    });

	    /* Sidebar tree view */
	    $(".sidebar .treeview").tree();

	    /* 
	     * Make sure that the sidebar is streched full height
	     * ---------------------------------------------
	     * We are gonna assign a min-height value every time the
	     * wrapper gets resized and upon page load. We will use
	     * Ben Alman's method for detecting the resize event.
	     * 
	     **/
	    function _fix() {
	        //Get window height and the wrapper height
	        var height = $(window).height() - $("body > .header").height() - ($("body > .footer").outerHeight() || 0);
	        $(".wrapper").css("min-height", height + "px");
	        var content = $(".wrapper").height();
	        //If the wrapper height is greater than the window
	        if (content > height)
	            //then set sidebar height to the wrapper
	            $(".left-side, html, body").css("min-height", content + "px");
	        else {
	            //Otherwise, set the sidebar to the height of the window
	            $(".left-side, html, body").css("min-height", height + "px");
	        }
	    }
	    //Fire upon load
	    _fix();
	    //Fire when wrapper is resized
	    $(".wrapper").resize(function() {
	        _fix();
	        fix_sidebar();
	    });

	    //Fix the fixed layout sidebar scroll bug
	    fix_sidebar();
	});
	
	function fix_sidebar() {
	    //Make sure the body tag has the .fixed class
	    if (!$("body").hasClass("fixed")) {
	        return;
	    }
	}
	
	module.exports = $.noConflict(true);
});