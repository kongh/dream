<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<table>
<dm:auth resource="/sm/resource/children">
<c:forEach items="${children}" var="node">
<tr 
	data-tt-id='${node.id}'
	data-tt-parent-id='${node.parentId}'
	data-tt-branch="${!node.leaf}"
	data-path="${ctx}/sm/resource/children?id=${node.id}"
>
	<td>
		<span class='<c:if test="${node.leaf == true}">file</c:if><c:if test="${node.leaf == false}">folder</c:if>'>
			${node.name}
		</span>
	</td>
	<td>${node.code}</td>
	<td>${node.typeAlias}</td>
	<td>${node.url}</td>
	<td>${node.iconCls}</td>
	<td>${node.comments}</td>
</tr>
</c:forEach>
</dm:auth>
</table>