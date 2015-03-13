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
				<h4 class="modal-title">修改</h4>
			</div>
			<div class="modal-body">
				<form id="sm-resource-update-form" class="form-horizontal"
					role="form">
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
						type="hidden" name="parentId" value="${parentId}">
					<%--隐藏结束开始 --%>
					<div class="form-group">
						<label for="sm-resource-update-name"
							class="col-sm-2 control-label">资源名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-update-name" name="name" placeholder="资源名称">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-update-code"
							class="col-sm-2 control-label">资源编码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-update-code" name="code" placeholder="资源编码">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-update-type"
							class="col-sm-2 control-label">资源类型</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-update-type" name="type" placeholder="资源类型">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-update-url" class="col-sm-2 control-label">资源路径</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-update-url" name="url" placeholder="资源路径">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-update-iconCls"
							class="col-sm-2 control-label">资源图标</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-resource-update-iconCls" name="iconCls"
								placeholder="资源图标">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-resource-update-comments"
							class="col-sm-2 control-label">备注</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="sm-resource-update-comments"
								name="comments" rows="3" placeholder="备注"></textarea>
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