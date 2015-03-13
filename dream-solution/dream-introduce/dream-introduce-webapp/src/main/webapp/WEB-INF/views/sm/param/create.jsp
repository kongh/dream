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
				<form id="sm-param-create-form" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="sm-param-create-name" class="col-sm-2 control-label">参数名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-param-create-name"
								name="name" placeholder="参数名称">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-param-create-code" class="col-sm-2 control-label">参数编码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-param-create-code"
								name="code" placeholder="参数编码">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-param-create-value" class="col-sm-2 control-label">参数值</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								id="sm-param-create-value" name="value" placeholder="参数值">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-param-create-comments"
							class="col-sm-2 control-label">备注</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="sm-param-create-comments"
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