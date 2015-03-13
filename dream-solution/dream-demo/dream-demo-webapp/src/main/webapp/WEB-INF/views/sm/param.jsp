<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	Ext.require([
	             'Dream.app.*'
	]);

	Ext.onReady(function(){
		 var store = Ext.create('Dream.app.store.sm.Param');
		 var grid = Ext.create('Dream.app.view.sm.Param',{
			title:'xxxx',
			layout:'fit',
			store:store,
			columns:[
			         {
			        	 text:'1',
						 dataIndex:'value'
			         }
			]
		});
		viewer.center.add(grid);
	});
</script>
</head>

<body>
</body>
</html>