<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<p>${fileRemoved}</p>
		<p>
			<a href=<%=request.getHeader("Referer")%>>Back</a>&nbsp; <a
				href="/BPS_BIRT_Reports/login">${frontpageRedirect}</a>
		</p>
	</div>
	<jsp:include page="../components/footer.jsp" />
</body>
</html>