<%@ page session="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,com.mongodb.DBObject"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link href="resources/css/showSchedules.css" rel="stylesheet"
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
<link rel="shortcut icon"
	href="/BPS_BIRT_Reports/resources/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BPS Reports</title>
</head>
<body>
	<div class="header">
		 <img
			class="logo" src="/BPS_BIRT_Reports/resources/images/dxclogo.jpg">
		
		<h1>
			BPS Reports
		</h1>
		<br> <br>
	</div>
	<c:if test="${login.username != null}">
		<div align="center" id="div">
			<div>
				<p>Schedule for ${reportName} of ${client}</p>
			</div>

			<br> <br>

			<table class="reports">
				<tr>
					<th>Report Name</th>
					<th>Schedules</th>
				</tr>


				<%-- <c:forEach items="${map}" var="entry">
         ${entry.key}<br> 
        <td>${entry.value}</td>
    </c:forEach> --%>
				<tr>
					<td>${reportName}</td>
					<td>${schedules}</td>

				</tr>



			</table>

			<c:if test="${empty histories}">
				<h3 style="color: red;">${noHistory}</h3>
			</c:if>
			<br> <br>
			<div class="pagination">

				<c:forEach var="i" begin="1" end="${pageCount}">
					<a <c:if test="${pageNumber== i}">class="active"</c:if>
						href="showHistory?report_name=${reportName}&client_name=${client}&page_number=${i}&recordsPerPage=${recordsPerPage}">${i}</a>
				</c:forEach>


			</div>
			<!-- <br> <br>
			<table id="test3" class="reports">
			</table> -->
		</div>
	</c:if>
	<c:if test="${login.username == null}">
		<%
			String redirectURL = "sessionexpired";
				response.sendRedirect(redirectURL);
		%>
	</c:if>




	<%--  recordByTitle is from the controller :    
    <% List<DBObject> cursorRecordByTitle  = recordByTitle %> --%>

	<%--  <%  %> --%>

	<%-- <c:if test="${not empty recordByTitle}">

		<ul>
			<c:forEach var="listValue" items="${recordByTitle}">
			
				<li>${listValue}</li> 
				     <c:forEach var="entry" items="${listValue}"> 
				     <tr><td>
				     <c:out value="${entry.key}"/></td>
				      <td><c:out value="${entry.value}"/> </td>
				      
				      </tr> </c:forEach>		
			</c:forEach>
		</ul>

	</c:if> --%>

	<%-- <c:forEach items="${recordByTitle}" var="map">
    <c:forEach items="${map}" var="entry">
        ${entry.key}<br>
        ${entry.value}<br>
    </c:forEach>
</c:forEach>  --%>

	<%-- <c:forEach items="${recordByTitle}" var="listItem">
        <table class="reports">
  <tr>
      <td>${listItem}</td>
  <tr> </table>
  </c:forEach>  --%>

	<%--  <c:forEach items="${recordByTitle}" var="map">
    <c:forEach items="${map}" var="entry">
         ${entry.key}<br> 
        <td>${entry.value}</td>
    </c:forEach>
</c:forEach> --%>






	<jsp:include page="../components/footer.jsp" />
</body>
</html>