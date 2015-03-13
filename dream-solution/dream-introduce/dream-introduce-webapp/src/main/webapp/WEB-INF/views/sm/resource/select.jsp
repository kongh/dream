<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>


<table id="sm-resource-select-table" class="table table-bordered">
	<thead>
		<tr>
			<th>资源名称</th>
			<th>资源编码</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${select}" var="node">
			<tr data-tt-id='${node.id}' data-tt-parent-id='${node.parentId}'>
				<td>
				<c:if test="${not empty node.parentId }">
				<input type="checkbox" name="select" value="${node.id}" <c:if test="${node.checked}">checked='checked'</c:if>/>
				</c:if>
				<span
					class='<c:if test="${node.leaf == true}">file</c:if><c:if test="${node.leaf == false}">folder</c:if>'>${node.name}
				</span></td>
				<td>${node.code}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>