<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<br>
<div class="btn-toolbar" role="toolbar">
	<div class="btn-group">&nbsp;</div>
	<dm:auth resource="/sm/user/create">
		<div class="btn-group">
			<button type="button" class="btn btn-default add">新增</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/user/update">
		<div class="btn-group">
			<button type="button" class="btn btn-default update">修改</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/user/delete">
		<div class="btn-group">
			<button type="button" class="btn btn-default delete">删除</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/user/page">
		<div class="btn-group">
			<button type="button" class="btn btn-default refresh">刷新</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/userRole/relative">
		<div class="btn-group">
			<button type="button" class="btn btn-default relative">关联角色</button>
		</div>
	</dm:auth>
</div>