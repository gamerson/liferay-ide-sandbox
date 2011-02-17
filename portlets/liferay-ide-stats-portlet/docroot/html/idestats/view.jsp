<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<portlet:defineObjects />

<jsp:useBean id="totalDownloadCount" type="java.lang.Integer" scope="request"></jsp:useBean>

<c:if test="${totalDownloadCount != -1}">
	 Liferay IDE Total Download Count = <c:out value="${totalDownloadCount}"></c:out>
</c:if>

<c:if test="${totalDownloadCount == -1}">
	 Liferay IDE Stats are still processing...
</c:if>