<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="container" align="right">
	<a href="<c:url value='/'/>"> Home</a>
	| Welcome :<c:out value='${pageContext.request.userPrincipal.name}${MyRoles}'/>
	<sec:authorize access="!isAuthenticated()">
    	| <a href="<c:url value='login'/>">Login</a>
    	| <a href="<c:url value='auth'/>">Register</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        | <a href="<c:url value='logout'/>">Logout</a>
    </sec:authorize>
</div>