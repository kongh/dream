<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/static/extjs/resources/ext-theme-crisp/ext-theme-crisp-all.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/app/css/main.css"/>
<script type="text/javascript" src="${ctx}/static/extjs/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/static/extjs/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/extjs/ext-theme-crisp.js"></script>
<script type="text/javascript" src="${ctx}/static/modules/init.js"></script>
<script type="text/javascript">

	Ext.onReady(function(){
		
		var viewModel = Ext.create('Ext.app.ViewModel',{
			data:{
				bindName:'hello,I`m view modeli9i9i',
				bindHtml:'binding contenti9ji',
				//bindDockedItems:
			    bindText:'bind点我'
			}
		});
		
		var panel = Ext.create('Ext.panel.Panel',{
			viewModel:viewModel,
			renderTo:Ext.getBody(),
			bind:{
				title:'{bindName}',
				html:'{bindHtml}',
			},
			dockedItems:[{
				xtype:'toolbar',
				dock:'top',
				items:[{
					bind:{
						text:'{bindText}'
					},
					iconCls: 'Add',
					handler:function(){
						viewModel.set('bindText','[bind点我]被点');
					}
				}]
			}]
		});
	});
</script>
</head>

<body>
</body>
</html>