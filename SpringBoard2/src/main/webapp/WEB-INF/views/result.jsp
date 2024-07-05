<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>result.jsp</h1>
	
<%-- 	${paramMap } --%>
	
	아이디 : ${paramMap.userid }<hr>
	이름 : ${paramMap.username }<hr>
	<c:forEach var="fileName" items="${paramMap.fileNameList }" varStatus="i">
		<c:set var="tmp" value="${fileName.substring(fileName.lastIndexOf('.')) }"/>
		${fileName.substring(fileName.lastIndexOf('.')) }
		파일${i.count} : <a href="/download?fileName=${fileName }">${fileName }</a><hr>
		<c:if test="${tmp=='.jpg' or tmp=='.gif'}">
		<img src = "/download?fileName=${fileName }"><hr>
		</c:if>
	</c:forEach>
</body>
</html>