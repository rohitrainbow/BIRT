function getReports() {
	// get the form values
	var clientName = $('#inputClient').val();
	$('#reportTable').html("");
	if (clientName) {
		$.ajax({
			type : "POST",
			url : "repList",
			data : {
				name : clientName
			},
			success : function(result) {
				$('#reportTable').html(result);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
}

function getRoleBasedClients() {
	var role = $('#inputRole').val();
	if (role) {
		$.ajax({
			type : "POST",
			url : "roleSet",
			data : {
				name : role
			},
			success : function(result) {
				window.location.href = "frontpage";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}	
}

