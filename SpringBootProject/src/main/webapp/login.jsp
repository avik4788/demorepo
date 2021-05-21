<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Page</title>
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet" />
	<link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<form method="POST" action="${contextPath}/login" class="form-signin">
			<h2 class="form-heading">Log In</h2>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span>
				<input name="username" type="text" class="form-control" placeholder="Username" autofocus="true" /> 
				<input name="password" type="password" class="form-control" placeholder="Password" />
				<span>${error}</span>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
				<h4 class="text-center">
					<a href="${contextPath}/registration">Create a User account</a>
				</h4>
				<h4 class="text-center">
					<a href="${contextPath}/adminregistration">Create an Admin account</a>
				</h4>
			</div>
		</form>
	</div>
</body>
</html>

<style>
	.paddingBottom{
		padding: 0 px;
	}
</style>