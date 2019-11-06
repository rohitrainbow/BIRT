<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon"
	href="/BPS_BIRT_Reports/resources/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="resources/js/jquery.js" type="text/javascript"></script>
<script src="resources/js/jquery-ui.min.js" type="text/javascript"></script>
<script type="text/javascript">
	window.history.forward();
	window.onunload = function () {
		$.ajax({
			type : "GET",
			cache : false,
			url : "logout",
			success : function(result) {
				window.location.href = "sessionexpired";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	function noBack() {
		window.history.forward();
	}

	var idleTime = 0;
	$(document).ready(function() {
		//Increment the idle time counter every minute.
		var idleInterval = setInterval(timerIncrement, 60000); // 1 minute

		//Zero the idle timer on mouse movement.
		$(this).mousemove(function(e) {
			idleTime = 0;
		});
		$(this).keypress(function(e) {
			idleTime = 0;
		});
	});

	function timerIncrement() {
		idleTime = idleTime + 1;
		if (idleTime > 9) { // 10 minutes
			{
				// get the form values		
				$.ajax({
					type : "GET",
					cache : false,
					url : "logout",
					success : function(result) {
						window.location.href = "sessionexpired";
					},
					error : function(e) {
						alert('Error: ' + e);
					}
				});

			}

		}
	}
</script>
</head>
<body>

</body>
</html>
