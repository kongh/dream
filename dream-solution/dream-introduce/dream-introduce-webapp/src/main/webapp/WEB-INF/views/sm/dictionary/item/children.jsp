<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<table>
<dm:auth resource="/sm/dictionary/item/children">
<c:forEach items="${children}" var="node">
<tr 
	data-tt-id='${node.id}'
	data-tt-parent-id='${node.parentId}'
	data-tt-branch="${!node.leaf}"
	data-path="${ctx}/sm/dictionary/item/children?id=${node.id}"
>
	<td>
		<span class='<c:if test="${node.leaf == true}">file</c:if><c:if test="${node.leaf == false}">folder</c:if>'>
			${node.name}
		</span>
	</td>
	<td>${node.value}</td>
	<td>${node.comments}</td>
</tr>
</c:forEach>
</dm:auth>
</table>