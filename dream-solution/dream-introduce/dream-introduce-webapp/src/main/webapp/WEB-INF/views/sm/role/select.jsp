<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>


<table id="sm-role-select-table" class="table table-bordered">
	<thead>
		<tr>
			<th>资源名称</th>
			<th>资源编码</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${select}" var="item">
			<tr data-tt-id='${item.id}'>
				<td>
				<input type="checkbox" name="select" value="${item.id}" <c:if test="${item.checked}">checked='checked'</c:if>/>
				<span>
				${item.name}
				</span>
				</td>
				<td>${item.code}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>