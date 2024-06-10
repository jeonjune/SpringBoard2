<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>

<%@ include file="include/header.jsp" %>

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<input type="button" value="버튼" class="btn-lg btn-sucess">

<%@ include file="include/footer.jsp" %>