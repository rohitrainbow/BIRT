<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon"
	href="/BPS_BIRT_Reports/resources/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BPS Reports</title>
</head>
<body>
	<div align="center">
		<p>${session}
		</p>
		<p>
			<a href="/BPS_BIRT_Reports/login">${loginRedirect}</a>
		</p>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>