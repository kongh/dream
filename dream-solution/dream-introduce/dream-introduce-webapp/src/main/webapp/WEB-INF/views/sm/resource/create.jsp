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
				<form id="sm-resource-create-form" class="form-horizontal"
					role="form">
					<div class="form-group">
						<label for="sm-resource-create-name"
							class="col-sm-2 control-label">资源名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-create-name" name="name" placeholder="资源名称">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-create-code"
							class="col-sm-2 control-label">资源编码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-create-code" name="code" placeholder="资源编码">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-create-type"
							class="col-sm-2 control-label">资源类型</label>
						<div class="col-sm-10">
								<select id="sm-resource-create-type"  name="type" style="width:100%;">
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-create-url" class="col-sm-2 control-label">资源路径</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-create-url" name="url" placeholder="资源路径">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-create-iconCls"
							class="col-sm-2 control-label">资源图标</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-create-iconCls" name="iconCls"
								placeholder="资源图标">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-create-comments"
							class="col-sm-2 control-label">备注</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="sm-resource-create-comments"
								name="comments" rows="3" placeholder="备注"></textarea>
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