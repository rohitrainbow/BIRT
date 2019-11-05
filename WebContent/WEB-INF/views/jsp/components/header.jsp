<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<script src="resources/js/action.js"></script>
<head>
<link rel="shortcut icon"
	href="/BPS_BIRT_Reports/resources/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BPS Reports</title>
</head>
<body>

	<div class="header">
		<a href="https://www.dxc.technology/" target="_blank"> <img class="logo"
			src="/BPS_BIRT_Reports/resources/images/dxclogo.jpg">
		</a>
		<h1>
			<a class="links" href="/BPS_BIRT_Reports/frontpage">BPS Reports</a>
		</h1>
		<br> <br>
		<c:if test="${login.username != null }">
			<table WIDTH="100%">
				<tr>
					<td style="text-align: left; color: white;">${login.username}<a style="color: white;" href="/BPS_BIRT_Reports/roles">(${login.role})</a></td>
					<td style="text-align: right"><a class="links"
						href="/BPS_BIRT_Reports/logout">Logout</a></td>

				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>