<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<div id="addModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">新增</h4>
			</div>
			<div class="modal-body">
				<form id="sm-user-create-form" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="sm-user-create-account" class="col-sm-2 control-label">账户</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-user-create-account" name="account" placeholder="账户">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-user-create-name" class="col-sm-2 control-label">名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-user-create-name"
								name="name" placeholder="名称">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-user-create-phone" class="col-sm-2 control-label">手机</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-user-create-phone"
								name="phone" placeholder="手机">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-user-create-email" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-user-create-email"
								name="email" placeholder="邮箱">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary create">保存</button>
				<button type="button" class="btn btn-default reset">重置</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->