<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />


<title>Insert title here</title>
</head>
<body>
	<table class="jobs">
		
		<c:forEach var="job" items="${jobs}">
		<tr>
	
		<td><c:out value="${job.name}"></c:out></td>
		
		<td><c:out value="${job.email}"></c:out></td>
		
		<td><c:out value="${job.text}"></c:out></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>