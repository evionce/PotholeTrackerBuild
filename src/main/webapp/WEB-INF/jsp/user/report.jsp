<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<script>
      			function initMap() {
       				var columbus_center = {lat: 39.9612, lng: -82.9988};
      				var map = new google.maps.Map(document.getElementById('map'), {
       				zoom: 12,
       				center: columbus_center,
       				styles: [{"stylers":[{"saturation":-100},{"gamma":1}]},{"elementType":"labels.text.stroke","stylers":[{"visibility":"off"}]},{"featureType":"poi.business","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"poi.business","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"poi.place_of_worship","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"poi.place_of_worship","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"geometry","stylers":[{"visibility":"simplified"}]},{"featureType":"water","stylers":[{"visibility":"on"},{"saturation":50},{"gamma":0},{"hue":"#50a5d1"}]},{"featureType":"administrative.neighborhood","elementType":"labels.text.fill","stylers":[{"color":"#333333"}]},{"featureType":"road.local","elementType":"labels.text","stylers":[{"weight":0.5},{"color":"#333333"}]},{"featureType":"transit.station","elementType":"labels.icon","stylers":[{"gamma":1},{"saturation":50}]}]
      			  });
       				
      				var marker = null;
       				map.addListener('click', function(e) {
       					if (marker != null) marker.setMap(null);
       					marker = new google.maps.Marker({
       						position: e.latLng,
       					    map: map
       					});
       					
       					$('#latitude').val(e.latLng.lat());
       					$('#longitude').val(e.latLng.lng());
       					$('#submitPotholeButton').show();
       				});
       				$('body').keyup(function (event) {
       					if(event.keyCode === 32){
       						if (marker != null) marker.setMap(null);
       						console.log(event);
       						marker = new google.maps.Marker({
           						position: event.latLng,
           					    map: map
           					});
           					
           					$('#latitude').val(event.latLng.lat());
           					$('#longitude').val(event.latLng.lng());
       					}
       				});
      			}
    		</script>
	<script async defer
  		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPIMWr5QAzWtV1_7GjSOuVXUF4WNxXgBg&callback=initMap">
	</script>
</head>
<body>

<c:import url="/WEB-INF/jsp/common/navbar.jsp"/>


<div class="container">
	<div class="row">
		<div class="col-md-8">
			<div id="map" style="width: 100$; height: 600px;"></div>
		</div>
		<div class="col-md-4">
            <div class="wrap">
                <p class="form-title">
                    Report Pothole</p>
                    <c:url var = "formAction" value ="/user/report"/>
                <form method= "POST" action="${formAction}" class="login" id="report">
                <input name= "latitude" id="latitude" type="text" placeholder="Latitude" readonly="readonly"/>
                <input name= "longitude" id="longitude" type="text" placeholder="Longitude" readonly="readonly"/>
                <input name= "comments" type="text" placeholder="Comments" />
                <input type = "hidden" name= "CSRF_TOKEN" value = "${CSRF_TOKEN}"/>
                <input id="submitPotholeButton" type="submit" value="report" class="btn btn-success btn-sm" style="display:none" />          
                </form>
            </div>
        </div>
	</div>
</div>
</body>
</html>