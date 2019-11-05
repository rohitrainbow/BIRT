<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="resources/js/jquery.js" type="text/javascript"></script>
<script src="resources/js/jquery-ui.min.js" type="text/javascript"></script>
<head>
<link rel="shortcut icon"
	href="/BPS_BIRT_Reports/resources/images/favicon.ico" />
<link href="resources/css/login.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<jsp:include page="../components/header.jsp" />
	<br>
	<br>
	<br>
	<form:form id="loginForm" modelAttribute="login" action="loginProcess"
		method="post">
		<table align="center">
			<tr>
				<td><form:label path="username">Username: </form:label></td>
				<td><form:input path="username" name="username" id="username" />
				</td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:password path="password" name="password"
						id="password" /></td>
			</tr>
			<tr class="blank_row">
				<td colspan="3"></td>
			</tr>
			<tr>
				<td colspan="2" class="errormessage">${errorMessage}</td>
			</tr>
			<tr class="blank_row">
				<td colspan="3"></td>
			</tr>
			<tr>

				<td align="center" colspan="2"><form:button id="login"
						name="login">Login</form:button></td>
						
			</tr>
			<tr></tr>
		</table>
	</form:form>
	<jsp:include page="../components/footer.jsp" />
</body>