 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="container text-center">
	<h1>Welcome</h1>
	<h3>Join our growing community!</h3>
		<form:form action="/login/register" method="post" modelAttribute="user">
			<p class="text-danger"><form:errors path="username"/></p>
			<p class="text-danger"><form:errors path="email"/></p>
			<p class="text-danger"><form:errors path="password"/></p>
			<p class="text-danger"><form:errors path="confPassword"/></p>
			<table class="table table-bordered table-primary m-3">
				<thead>
					<tr>
						<td colspan="2">Registration</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><form:label path="username">Username:</form:label></td>
						<td><form:input path="username"/></td>
					</tr>
					<tr>
						<td><form:label path="email">Email:</form:label></td>
						<td><form:input path="email"/></td>
					</tr>
					<tr>
						<td><form:label path="password">Password:</form:label></td>
						<td><form:input type="password" path="password"/></td>
					</tr>
					<tr>
						<td><form:label path="confPassword">Confirm Password:</form:label></td>
						<td><form:input type="password" path="confPassword"/></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class=""/></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		<form:form action="/login/login" method="post" modelAttribute="loginUser">
			<p class="text-danger"><form:errors path="email"/></p>
			<p class="text-danger"><form:errors path="password"/></p>
			<table class="table table-bordered table-primary m-3">
				<thead>
					<tr><td colspan="2">Login</td></tr>
				</thead>
				<tbody>
					<tr>
						<td><form:label path="email">Username:</form:label></td>
						<td><form:input path="email"/></td>
					</tr>
					<tr>
						<td><form:label type="password" path="password">Password:</form:label></td>
						<td><form:input type="password" path="password"/></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit"/></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>