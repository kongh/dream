<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<br>
<div class="btn-toolbar" role="toolbar">
	<div class="btn-group">&nbsp;</div>
	<dm:auth resource="/sm/role/create">
		<div class="btn-group">
			<button type="button" class="btn btn-default add">新增</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/role/update">
		<div class="btn-group">
			<button type="button" class="btn btn-default update">修改</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/role/delete">
		<div class="btn-group">
			<button type="button" class="btn btn-default delete">删除</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/role/page">
		<div class="btn-group">
			<button type="button" class="btn btn-default refresh">刷新</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/roleResource/relative">
		<div class="btn-group">
			<button type="button" class="btn btn-default relative">关联资源</button>
		</div>
	</dm:auth>
</div>