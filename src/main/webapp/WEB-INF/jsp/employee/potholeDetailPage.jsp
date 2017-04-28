<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<script src="/capstone/js/custom/validateRegisterLogin.js"></script>
	<script>
	var pothole = ${potholeAsJson};
	var markers = [];
	var prev_infowindow = false;
	
	function show_detail(index){
		markers[index].infoWindow.open(map, markers[index]);
	}
	
	function initMap() {
		  var myCenter = {lat: ${pothole.latitude}, lng: ${pothole.longitude}};

		  var map = new google.maps.Map(document.getElementById('map'), {
		    zoom: 16,
		    center: myCenter,
		    styles: [{"stylers":[{"saturation":-100},{"gamma":1}]},{"elementType":"labels.text.stroke","stylers":[{"visibility":"off"}]},{"featureType":"poi.business","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"poi.business","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"poi.place_of_worship","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"poi.place_of_worship","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"geometry","stylers":[{"visibility":"simplified"}]},{"featureType":"water","stylers":[{"visibility":"on"},{"saturation":50},{"gamma":0},{"hue":"#50a5d1"}]},{"featureType":"administrative.neighborhood","elementType":"labels.text.fill","stylers":[{"color":"#333333"}]},{"featureType":"road.local","elementType":"labels.text","stylers":[{"weight":0.5},{"color":"#333333"}]},{"featureType":"transit.station","elementType":"labels.icon","stylers":[{"gamma":1},{"saturation":50}]}]
		  });

		  for(i = 0; i < 1; i++){
				var marker = new google.maps.Marker({
					map: map,
					draggable: false,
					animation: google.maps.Animation.DROP,
					position: {
						lat: pothole.latitude,
						lng: pothole.longitude
					}
				})
				if(pothole.status == 0){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png')
				} else if(pothole.status == 1){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/yellow-dot.png')
				} else if(pothole.status == 2){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png')
				} else if(pothole.status == 3){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png')
				} 
				
				var contentString = '<div style="width:250px">'+ "<span>Date Reported: </span>" + pothole.date_reported + "<br><br>";
				if(pothole.status == 0){
					contentString += "<span>Status: </span> Reported" + "<br><br>";
				} else if (pothole.status == 1){
					contentString += "<span>Status: </span> Confirmed" + "<br><br>";
				} else if (pothole.status == 2){
					contentString += "<span>Status: </span> Scheduled repair for: " + "<br><br>";
				} else if (pothole.status == 3){
					contentString += "<span>Status: </span> Repair completed" + "<br><br>";
				}
				if(pothole.comments.length > 0){
					contentString += "<p>Comments: </p>" +pothole.comments;
				}
				contentString += "</div>";
				marker.infoWindow = new google.maps.InfoWindow({
					content: contentString
				});
				marker.addListener('click', function(){
					
					if(prev_infowindow) {
						prev_infowindow.close();
					}
					prev_infowindow = this.infoWindow;
					this.infoWindow.open(map, this)
				});
				markers[i] = marker;
			}
		  
		}
	
	
    </script>
	<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPIMWr5QAzWtV1_7GjSOuVXUF4WNxXgBg&callback=initMap">
	</script>
</head>
<body>

<c:import url="/WEB-INF/jsp/common/navbar.jsp"/>

<div class="container">
	<div id="map" style="width: 500px; height: 400px;" class="col-md-6">
	</div>
	<c:url var="formAction" value="/employee/potholeDetail"/>
	<form  method="POST" action="${formAction}" style="width:100%" id="potholeEditForm" name="editForm">
	<div class = "col-md-5">
        <p class="form-title">EDIT POTHOLE</p>
        <fieldset class="form-group">
    <legend style="text-align:center; color: white; font-family: Open Sans, sans-serif; font-size: 18px; font-wieght: 550; text-transform: uppercase; letter-spacing: 4px">Status</legend>
    <div class="form-check">
      <label class="form-check-label">
        <input type="radio" class="form-check-input" name="status" id="statusRadio1" value="0">
        Unconfirmed
      </label>
    </div>
    <div class="form-check">
    <label class="form-check-label">
        <input type="radio" class="form-check-input" name="status" id="statusRadio2" value="1">
        Confirmed - Awaiting Scheduling
      </label>
    </div>
    <div class="form-check">
    <label class="form-check-label">
        <input type="radio" class="form-check-input" name="status" id="statusRadio3" value="2">
        Scheduled
      </label>
    </div>
    <div class="form-group" style="display: none;" id="repairDateInputDiv" >
    	<label for="exampleInputEmail1" style="color:white; letter-spacing: 1px">Scheduled Repair Date</label>
    	<input type="date" class="form-control" name="scheduledDate" id="repairDateInputBox" aria-describedby="emailHelp" placeholder="Enter a date">
    	<small id="emailHelp" class="form-text text-muted" style="color:white">Please enter a date in for the format MM/DD/YYYY.</small>
  	</div>
    <div class="form-check">
    <label class="form-check-label">
        <input type="radio" class="form-check-input" name="status" id="statusRadio4" value="3">
        Repair Completed
      </label>
    </div>
  </fieldset>
  <fieldset class="form-group">
    <legend style="text-align:center; color: white; font-family: Open Sans, sans-serif; font-size: 18px; font-wieght: 550; text-transform: uppercase; letter-spacing: 4px">Severity</legend>
    <div class="form-check">
      <label class="form-check-label">
        <input type="radio" class="form-check-input" name="severity" id="severityRadio1" value="0">
        Low
      </label>
    </div>
    <div class="form-check">
    <label class="form-check-label">
        <input type="radio" class="form-check-input" name="severity" id="severityRadio2" value="1">
        Moderate
      </label>
    </div>
    <div class="form-check">
    <label class="form-check-label">
        <input type="radio" class="form-check-input" name="severity" id="severityRadio3" value="2">
        Severe
      </label>
    </div>
  </fieldset> 
     <button type="submit" class="btn btn-success btn-sm" style="width: 100%; color: white; font-family: Open Sans, sans-serif; font-size: 18px; font-weight: 550; text-transform: uppercase; letter-spacing: 4px ">Confirm Edits</button>
	</div>
	
	<input type = "hidden" name= "CSRF_TOKEN" value = "${CSRF_TOKEN}"/>
	<input type = "hidden" name="pothole_id" value="${pothole.id}"/>
	</form>
</div>


<script>
	var pothole = ${potholeAsJson};
	if (pothole.status === 0){
		$('#statusRadio1').attr('checked',true);
		$('#statusRadioHidden1').attr('checked',true);
	} else if(pothole.status === 1){
		$('#statusRadio2').attr('checked',true);
		$('#statusRadioHidden2').attr('checked',true);
	} else if(pothole.status === 2){
		$('#statusRadio3').attr('checked',true);
		$('#statusRadioHidden3').attr('checked',true);
	} else {
		$('#statusRadio4').attr('checked',true);
		$('#statusRadioHidden4').attr('checked',true);
	}
	
	if (pothole.severity === 0){
		$('#severityRadio1').attr('checked',true);
		$('#severityHiddenRadio1').attr('checked',true);
	} else if(pothole.status === 1){
		$('#severityRadio2').attr('checked',true);
		$('#severityHiddenRadio2').attr('checked',true);
	} else {
		$('#severityRadio3').attr('checked',true);
		$('#severityHiddenRadio3').attr('checked',true);
	}
	
	$("input[name='status']").change(function() {
		if($(this).val()=="2"){
			$("#repairDateInputDiv").show();
			console.log($(this));
		} else {
			$("#repairDateInputDiv").hide();
		}
	})
	
</script>			

</body>
</html>