<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />
</head>
<body>

<c:import url="/WEB-INF/jsp/common/navbar.jsp"/>

<div class="container">
	<table class="table ui-helper-center">
		<thead>
			<tr style="background-color:#FFF">
				<th style="text-align: center;">Pothole ID</th>
				<th style="text-align: center;">Status</th>
				<th style="text-align: center;">Date Reported</th>
				<th style="text-align: center;">Latitude</th>
				<th style="text-align: center;">Longitude</th>
				<th style="text-align: center;">Severity</th>
				<th style="text-align: center;">Comments</th>
				<th style="text-align: center;">Scheduled</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="tableBody">
		</tbody>
	</table>
</div>

<script>	
			function populateTable(potholes){

				for(i = 0; i < potholes.length; i++){
					console.log(potholes[i]);
					var pothole_id = potholes[i].id;
					var status = potholes[i].status;
					if(status === 0){
						status = "Unconfirmed"
					}
					if(status === 1){
						status = "Confirmed"
					}
					if(status === 2){
						status = "Scheduled"
					}
					if(status === 3){
						status = "Repaired"
					}
					var date_reported = potholes[i].date_reported;
					var latitude = potholes[i].latitude;
					var longitude = potholes[i].longitude;
					var severity = potholes[i].severity;
					var severityAsString;
					var className;
					if(severity === 0){
						severityAsString = "Low";
						className = 'info';
					}
					else if(severity === 1){
						severityAsString = "Moderate";
						className = 'warning';
					}
					else if(severity === 2){
						severityAsString = "High";
						className = 'danger';
					} else {
						className = 'test';
					}
					
					var comments = potholes[i].comments;
					var scheduled_repair_date = (potholes[i].hasOwnProperty('date_scheduled_for_repair') === false || potholes[i].date_scheduled_for_repair === null) ? "Not Scheduled" : potholes[i].date_scheduled_for_repair;
					console.log(scheduled_repair_date);
					var editLink = "<a href='./potholeDetail?pothole_id=" + pothole_id + "'><button type='button' class ='editPotholeButton btn btn-success' style='letter-spacing: 0.0625em; text-transform: uppercase'>Edit</button></a>";
					var deleteLink = "<button type='button' pothole-id='" + pothole_id + "' class='deletePotholeButton btn btn-danger' style='letter-spacing: 0.0625em; text-transform: uppercase'>Delete</button>";
					console.log(deleteLink);

					var myRow = "<tr class='" + className + "'><td class='pothole_id'>"+ pothole_id +"</td><td>" + status + "</td><td>" + date_reported + "</td><td>" + latitude + "</td><td>" + longitude + "</td><td>" + severityAsString + "</td><td class='commentsTD'></td><td>" + scheduled_repair_date + "</td><td>" +editLink +"</td><td>" +deleteLink+ "</td></tr>";
					$('#tableBody').append(myRow);
					$('#tableBody td.commentsTD:last').text(comments);
				}
				
				$('.deletePotholeButton').on('click', function(event){
			    	$.ajax({
			    		url: "http://localhost:8080/capstone/employee/api/deletePothole",
						type: "POST",
						contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
						data: jQuery.param({ id: $(this).attr('pothole-id'), CSRF_TOKEN : getCSRFTokenValue()})
			    	}).done(function (data) {
						clearTable();
						populateTable(data);
						
					}).fail(function (xhr, status, error) {
						console.log(error);
					});
					return false;
			    });
			}
			
			function clearTable(){
				$('#tableBody').empty();
			}
			
			populateTable(${potholes});
		    
		    function getCSRFTokenValue() {return "${CSRF_TOKEN}"};
		    
		     
			
			</script>
			
			

</body>
</html>