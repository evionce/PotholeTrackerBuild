$(document).ready(function () {

	$('#searchForUserByEmailButton').on('click', function(event){
		$.ajax({
			url: "http://localhost:8080/capstone/admin/api/getUser?email=" + $('#emailInput').val(),
			type: "GET",
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function (data) {
			console.log(data);
			$('#userFirstName').val(data.firstName);
			$('#userLastName').val(data.lastName);
			$('#userPhone').val(data.phoneNumber);
			$('#userType').val(data.userType===1?"Regular App-User":"Employee");
			$('#toggleUserToType').show();
		}).fail(function (xhr, status, error) {
			console.log(error);
		});
		return false;
    });
	
	
});
