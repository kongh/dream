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
				<form id="sm-dictionary-create-form" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="sm-dictionary-create-name" class="col-sm-2 control-label">字典名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-dictionary-create-name"
								name="name" placeholder="字典名称">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-dictionary-create-code" class="col-sm-2 control-label">字典编码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sm-dictionary-create-code"
								name="code" placeholder="字典编码">
						</div>
					</div>
					<div class="form-group">
						<label for="sm-dictionary-create-comments"
							class="col-sm-2 control-label">备注</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="sm-dictionary-create-comments"
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