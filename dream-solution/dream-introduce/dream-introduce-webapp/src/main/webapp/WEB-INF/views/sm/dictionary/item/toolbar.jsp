<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<br>
<div class="btn-toolbar" role="toolbar">
	<div class="btn-group">&nbsp;</div>
	<dm:auth resource="/sm/dictionary/item/create">
		<div class="btn-group">
			<button type="button" class="btn btn-default add">新增</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/dictionary/item/update">
		<div class="btn-group">
			<button type="button" class="btn btn-default update">修改</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/dictionary/item/deleteNode">
		<div class="btn-group">
			<button type="button" class="btn btn-default delete">删除</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/dictionary/item/children">
		<div class="btn-group">
			<button type="button" class="btn btn-default refresh">刷新</button>
		</div>
	</dm:auth>
	<dm:auth resource="/sm/dictionary">
		<div class="btn-group">
			<button type="button" class="btn btn-default dictionary">返回</button>
		</div>
	</dm:auth>
</div>