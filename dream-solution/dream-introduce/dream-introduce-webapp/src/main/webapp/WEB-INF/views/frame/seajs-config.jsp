<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<script src="${ctx}/static/sea-modules/seajs/seajs/2.3.0/sea.js"></script>
<script src="${ctx}/static/sea-modules/seajs/seajs-css/2.3.0/seajs-css-debug.js"></script>
<script>
	//配置context
	var ctx = '${ctx}';
	//配置seajs
	seajs.config({
		base:"${ctx}/static/sea-modules/",
		alias: {
			/*******************************************************/
			//jquery and jquery plugin js
			"jquery" : "jquery/jquery/2.1.3/jquery-debug.js",
			"bootstrap" : "jquery/bootstrap/3.3.0/bootstrap.js",
			"datatables":"jquery/datatables/1.9.4/jquery.dataTables.js",
			"datatables-bootstrap":"jquery/datatables/1.9.4/dataTables.bootstrap.js",
			"treetable" : "jquery/treetable/3.2.0/jquery.treetable.js",
			"jquery-form" : "jquery/form/3.51.0/jquery.form.js",
			"treetable-ajax-persist" : "jquery/treetable/3.2.0/jquery.treetable-ajax-persist.js",
			//jquery and jquery plugin css
			"bootstrap-css":"jquery/bootstrap/3.3.0/css/bootstrap.min.css",
			/********************************************************/
			//coder base
			"sidebar" : "coder/sidebar/menu/1.0.0/dream.sidebar.menu.js",
			"table-curd-factory":"coder/table/crud/1.0.0/factory.js",
			"table-select-factory":"coder/table/select/1.0.0/factory.js",
			"treetable-curd-factory":"coder/treetable/crud/1.0.0/factory.js",
			"treetable-select-factory":"coder/treetable/select/1.0.0/factory.js",
			//app
			"app-base" : "coder/app/1.0.0/base.js",
			"app-util" : "coder/app/1.0.0/util.js",
			"app-login":"coder/app/1.0.0/modules/login/login.js",
			"app-dictionary":"coder/app/1.0.0/modules/dictionary/dictionary.js",
			"app-dictionary-item":"coder/app/1.0.0/modules/dictionary/item.js",
			"app-param":"coder/app/1.0.0/modules/param/param.js",
			"app-user":"coder/app/1.0.0/modules/user/user.js",
			"app-resource":"coder/app/1.0.0/modules/resource/resource.js",
			"app-resource-select":"coder/app/1.0.0/modules/resource/select.js",
			"app-role":"coder/app/1.0.0/modules/role/role.js",
			//css
			"datatables-bootstrap-css":"jquery/datatables/1.9.4/css/dataTables.bootstrap.css",
		 }
	});
	//配置app级别css
	seajs.use("bootstrap-css");
	seajs.use("coder/app/1.0.0/old/css/font-awesome.min.css");
	seajs.use("coder/app/1.0.0/old/css/ionicons.min.css");
	seajs.use("coder/app/1.0.0/old/css/AdminLTE.css");
	seajs.use("coder/app/1.0.0/base.css");
	//配置app级别js
	seajs.use("app-base");
</script>
<script>
//对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
//var time1 = new Date().format("yyyy-MM-dd HH:mm:ss");   
//var time2 = new Date().format("yyyy-MM-dd");  
Date.prototype.Format = function(fmt) 
{ //author: meizz 
var o = { 
 "M+" : this.getMonth()+1,                 //月份 
 "d+" : this.getDate(),                    //日 
 "h+" : this.getHours(),                   //小时 
 "m+" : this.getMinutes(),                 //分 
 "s+" : this.getSeconds(),                 //秒 
 "q+" : Math.floor((this.getMonth()+3)/3), //季度 
 "S"  : this.getMilliseconds()             //毫秒 
}; 
if(/(y+)/.test(fmt)) 
 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
for(var k in o) 
 if(new RegExp("("+ k +")").test(fmt)) 
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
return fmt; 
}
</script>