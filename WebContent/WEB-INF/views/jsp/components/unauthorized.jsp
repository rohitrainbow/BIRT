<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<script src="resources/js/action.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BPS Reports</title>
</head>
<body>
	<div align="center">
		<p style="color: red">You are not authorized to perform this
			operation.</p>
		<p>
			<a href="/BPS_BIRT_Reports/login">Navigate to Application</a>
		</p>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>