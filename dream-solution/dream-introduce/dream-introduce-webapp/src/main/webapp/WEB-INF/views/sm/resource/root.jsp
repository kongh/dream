<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<dm:auth resource="/sm/resource/root">
<tr 
	data-tt-id='${root.id}'
	data-tt-parent-id='${node.parentId}'
	data-tt-branch="${!root.leaf}"
	data-path="${ctx}/sm/resource/children?id=${root.id}"
>
	<td>
		<span class='<c:if test="${root.leaf == true}">file</c:if><c:if test="${root.leaf == false}">folder</c:if>'>
			${root.name}
		</span>
	</td>
	<td>${root.code}</td>
	<td>${root.type}</td>
	<td>${root.url}</td>
	<td>${root.iconCls}</td>
	<td>${root.comments}</td>
</tr>
</dm:auth>