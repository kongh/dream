<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
Ext.require([
             'Dream.app.view.sm.Resource'
]);

Ext.onReady(function(){
	Ext.Ajax.request({
	    url: 'sm/resource/root',
	    success: function(response, opts) {
	        var obj = Ext.decode(response.responseText);
	        if(obj.success){
	        	var grid = Ext.create('Dream.app.view.sm.Resource',{
	    			root:obj.data
	    		});
	    		viewer.center.add(grid);
	        }
	    },
	    failure: function(response, opts) {
	        console.log('server-side failure with status code ' + response.status);
	    }
	});
});
</script>
</head>

<body>
</body>
</html>