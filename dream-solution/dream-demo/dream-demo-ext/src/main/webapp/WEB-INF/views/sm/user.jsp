<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	Ext.require([
	             'Dream.app.*'
	]);
	
	Ext.onReady(function(){
		 var toolbarConfig = [];
		 
		 <dm:auth resource="/sm/user/create">
		 toolbarConfig.push({
				text:'新增',
				iconCls: 'Add',
				handler:function(){
					$this.addHandler();
				}
		 });
		 </dm:auth>
		 <dm:auth resource="/sm/user/update">
		 toolbarConfig.push({
				text:'修改',
				iconCls: 'Pencil',
				handler:function(){
					$this.updateHandler();
				}
			});
		 </dm:auth>
		 <dm:auth resource="/sm/user/delete">
		 toolbarConfig.push({
				text:'删除',
				iconCls: 'Bulletcross',
				handler:function(){
					var target = {
							grid:$this.grid,
							url:'sm/user/delete'
					};
					
					$this.deleteHandler(target);
				}
			});
		 </dm:auth>
		 <dm:auth resource="/sm/user/page">
		 toolbarConfig.push({
				text:'刷新',
				iconCls: 'Reload',
				handler:function(){
					var target = {
							grid:$this.grid
					};
					$this.refreshHandler(target);
				}
			});
		 </dm:auth>
		 <dm:auth resource="/sm/role/checkedList">
		 toolbarConfig.push({
				text:'关联角色',
				iconCls: 'Reload',
				handler:function(){
					var target = {
							grid:$this.grid
					};
					$this.relativeRole(target);
				}
			});
		 </dm:auth>
		 var $this = Ext.create('Dream.app.view.sm.User',{
			 toolbarConfig:toolbarConfig
		 });
		 viewer.center.add($this);
	});
</script>
</head>

<body>
</body>
</html>