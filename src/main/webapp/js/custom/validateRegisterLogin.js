$(document).ready(function () {
	$('#potholeEditForm').validate({
		rules: {
			scheduledDate: {
				scheduledMustHaveValidDate: true
			}
		}
	});
	
	$('#register').validate({
		rules: {
			email: {
				required: true,
				email: true
			},
			first_name: {
				required: true,
				rangelength: [3,32],
				letterswithbasicpunc: true
			},
			last_name: {
				required: true,
				rangelength: [3,32],
				letterswithbasicpunc: true
			},
			phoneNumber: {
				required: true,
				phoneUS: true
			},
			password: {
				required: true,
				minlength: 8,
				strongPassword: true
			},
			passwordMatch: {
				equalTo: '#password'
			}
		}
	});
	
	$('#login').validate({
		rules: {
			userName: {
				required: true,
				email: true
			},
			password: {
				required: true
			}
		}
	});
	
	$('#emailLookup').validate({
		rules: {
			email: {
				required: true,
				email: true
			}
		}
	});
	
	
	$.validator.addMethod("strongPassword", function (value, index) {
	    return value.match(/[A-Z]/) && value.match(/[a-z]/) && value.match(/\d/);  //check for one capital letter, one lower case letter, one num
	}, "Please enter a strong password (one capital, one lower case, and one number)");
	
	$.validator.addMethod("scheduledMustHaveValidDate", function(value, index){
		if($('#statusRadio3').is(':checked')){
			if($('#repairDateInputBox').val() === null){
				return false;
			}
			var today =new Date();
			var inputDate = new Date($('#repairDateInputBox').val());
			return inputDate > today;
		} else {
			return true;
		}
	}, "Please enter a valid date if you would like to mark this pothole as scheduled.");

});