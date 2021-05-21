<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageContext" value="${pageContext.request.contextPath}"></c:set>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet" />
	<link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<form:form method="POST" modelAttribute="userForm" class="form-signin">
			<h2 class="form-signin-header">Create your account</h2>
			<spring:bind path="userName">
				<div class="form-group ${status.error ? 'hasError' : ''}">
					<form:input type="text" path="userName" class="form-control" placeholder="Username" autofocus="true" />
					<form:errors path="userName"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="password">
				<div class="form-group ${status.error ? 'hasError' : ''}">
					<form:input type="password" path="password" class="form-control" placeholder="Password" autofocus="true" />
					<form:errors path="password"></form:errors>
				</div>
			</spring:bind>
			<spring:bind path="passwordConfirm">
				<div class="form-group ${status.error ? 'hasError' : ''}">
					<form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirm your password" autofocus="true" />
					<form:errors path="passwordConfirm"></form:errors>
				</div>
			</spring:bind>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>
	</div>
</body>
</html>