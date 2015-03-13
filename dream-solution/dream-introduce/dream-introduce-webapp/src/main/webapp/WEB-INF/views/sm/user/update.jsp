<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp"%>

<div id="updateModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">新增</h4>
			</div>
			<div class="modal-body">
				<form id="sm-user-update-form" class="form-horizontal" role="form">
					<%--隐藏字段开始 --%>
					<input type="hidden" name="id" value="${id}"> <input
						type="hidden" name="createUserId" value="${createUserId}">
					<input type="hidden" name="createDate"
						value="<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
					<input type="hidden" name="updateUserId" value="${updateUserId}">
					<input type="hidden" name="updateDate"
						value="<fmt:formatDate value="${updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
					<input type="hidden" name="sort" value="${sort}"> <input
						type="hidden" name="status" value="${status}"> <input
						type="hidden" name="password" value="${password}">

					<%--隐藏结束开始 --%>
					<div class="form-group">
						<label for="sm-user-update-account" class="col-sm-2 control-label">账户</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-user-update-account" name="account" placeholder="账户">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-user-update-name" class="col-sm-2 control-label">名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-user-update-name"
								name="name" placeholder="名称">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-user-update-phone" class="col-sm-2 control-label">手机</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-user-update-phone"
								name="phone" placeholder="手机">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-user-update-email" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-user-update-email"
								name="email" placeholder="邮箱">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary update">保存</button>
				<button type="button" class="btn btn-default reset">重置</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->