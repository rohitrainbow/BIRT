<%@ page session="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../components/header.jsp" />
<jsp:include page="../components/idleSession.jsp" />
<link href="resources/css/frontpage.css" rel="stylesheet"
	type="text/css">
<script src="resources/js/jquery.js" type="text/javascript"></script>
<script src="resources/js/jquery-ui.min.js" type="text/javascript"></script>
<script src="resources/js/action.js"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BPS Reports</title>
</head>
<body>
	<c:if test="${login.username != null}">
		<div align="center" id="div">
			<br> <br>
			<table class="select">
				<tr>
					<td align="center"><label>Select Client &nbsp; </label></td>
					<td><select name="selClient" id="inputClient"
						onchange="getReports()">
							<option value="" selected>-----Client Name------</option>
							<c:forEach var="client" items="${clients}">
								<option value="${client.name}">${client.name}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<br> <br>
			<table id="reportTable" class="reports">
			</table>
		</div>
		<c:if test="${empty clients}">
			<p class="errormessage">You are not assigned to any reports.</p>
		</c:if>
	</c:if>
	<c:if test="${login.username == null}">
		<%
			String redirectURL = "sessionexpired";
				response.sendRedirect(redirectURL);
		%>
	</c:if>
	<jsp:include page="../components/footer.jsp" />
</body>
</html>