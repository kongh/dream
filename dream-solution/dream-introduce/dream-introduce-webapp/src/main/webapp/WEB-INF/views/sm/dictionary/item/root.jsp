<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<dm:auth resource="/sm/dictionary/item/root">
<tr 
	data-tt-id='${root.id}'
	data-tt-parent-id='${node.parentId}'
	data-tt-branch="${!root.leaf}"
	data-path="${ctx}/sm/dictionary/item/children?id=${root.id}"
>
	<td>
		<span class='<c:if test="${root.leaf == true}">file</c:if><c:if test="${root.leaf == false}">folder</c:if>'>
			${root.name}
		</span>
	</td>
	<td>${root.value}</td>
	<td>${root.comments}</td>
</tr>
</dm:auth>