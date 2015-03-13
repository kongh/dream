<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<!DOCTYPE html>
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
	        	var toolbarConfig = [];
	        	<dm:auth resource="/sm/resource/create">
	    		toolbarConfig.push({
					text:'新增',
					iconCls: 'Add',
					handler:function(){
						$this.addHandler();
					}
				});
	    		</dm:auth>
	        	<dm:auth resource="/sm/resource/update">
	    		toolbarConfig.push({
					text:'修改',
					iconCls: 'Pencil',
					handler:function(){
						$this.updateHandler();
					}
				});
	    		</dm:auth>
	        	<dm:auth resource="/sm/resource/deleteNode">
	    		toolbarConfig.push({
					text:'删除',
					iconCls: 'Bulletcross',
					handler:function(){
						var target = {
								tree:$this.tree,
								url:'sm/resource/deleteNode'
						};
						
						$this.deleteHandler(target);
					}
				});
	    		</dm:auth>
	        	<dm:auth resource="/sm/resource/children">
	    		toolbarConfig.push({
					text:'刷新',
					iconCls: 'Reload',
					handler:function(){
						var target = {
								tree:$this.tree
						};
						var records = target.tree.getSelectionModel().getSelection();
						if(records.length > 0){
							var selId = records[0].get('id');
							var selNode = target.tree.getStore().getNodeById(selId);
							target.node = selNode;
						}
						$this.refreshHandler(target);
					}
				});
	    		</dm:auth>
	        	var $this = Ext.create('Dream.app.view.sm.Resource',{
	    			root:obj.data,
	    			toolbarConfig:toolbarConfig
	    		});
	    		viewer.center.add($this);
	        }
	    }
	});
});
</script>
</head>

<body>
</body>
</html>