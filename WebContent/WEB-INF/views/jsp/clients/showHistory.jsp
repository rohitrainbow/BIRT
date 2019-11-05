<%@ page session="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,com.mongodb.DBObject"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../components/header.jsp" />
<jsp:include page="../components/idleSession.jsp" />
<link href="resources/css/historyoptions.css" rel="stylesheet"
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
			<div>
				<p>
					History for <a
						href="\BPS_BIRT_Reports\frameset?__report=${reportName}.rptdesign&DBPARAM=${client}&login=${encryptedUserName}">${reportName}</a>
					of ${client}
				</p>
			</div>

			<br> <br>
			<c:if test="${not empty histories}">
				<div>
					<h4>
						Records Per Page:- <a
							href="showHistory?report_name=${reportName}&client_name=${client}&page_number=1&recordsPerPage=10">10</a>
						<a
							href="showHistory?report_name=${reportName}&client_name=${client}&page_number=1&recordsPerPage=15">15</a>
						<a
							href="showHistory?report_name=${reportName}&client_name=${client}&page_number=1&recordsPerPage=20">20</a>
						<a
							href="showHistory?report_name=${reportName}&client_name=${client}&page_number=1&recordsPerPage=40">40</a>
					</h4>

				</div>
			</c:if>
			<table class="reports">
				<tr>
					<th>S. No.</th>
					<th>Instance Time</th>
					<th>Title</th>
					<th>Created By</th>
					<th>Type</th>
					<!--  <th>Locale</th> -->
					<th>Parameters</th>
					<th>Email File</th>
				</tr>

				<c:forEach items="${histories}" var="history">
					<%-- <c:forEach items="${map}" var="entry">
         ${entry.key}<br> 
        <td>${entry.value}</td>
    </c:forEach> --%>
					<tr>
						<td>${history.number}</td>
						<td><a href="downloadFile?id=${history.id}">${history.startTime}</a></td>
						<td><a href="downloadFile?id=${history.id}">${history.title}</a></td>
						<td><a href="downloadFile?id=${history.id}">${history.createdBy}</a></td>
						<td><a href="downloadFile?id=${history.id}">${history.contentType}</a></td>
						<%-- <td>${map.Status}</td> --%>
						<td><a href="downloadFile?id=${history.id}">${history.parameters}</a></td>
						<td><a href='#'
							onclick='javascript:window.open("sendEmail?id=${history.id}&docType=${history.contentType}&reportName=${history.title}", "_blank", "scrollbars=1,resizable=1,height=300,width=450");'
							title='Send Email'>Send in Email</a></td>

					</tr>
				</c:forEach>



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