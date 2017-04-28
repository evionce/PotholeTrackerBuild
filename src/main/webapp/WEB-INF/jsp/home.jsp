<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="/WEB-INF/jsp/common/header.jsp" />
<script>
	var potholes = ${potholes};
	var markers = [];
	var prev_infowindow = false;
	for(i = 0; i < potholes.length; i++){
		console.log(potholes[i]);
	}
	function show_detail(index){
		markers[index].infoWindow.open(map, markers[index]);
	}
	
	window.initMap = function() {
		  var myCenter = {lat: 39.9612, lng: -82.9988};

		  var map = new google.maps.Map(document.getElementById('map'), {
		    zoom: 12,
		    center: myCenter,
		    styles: [{"stylers":[{"saturation":-100},{"gamma":1}]},{"elementType":"labels.text.stroke","stylers":[{"visibility":"off"}]},{"featureType":"poi.business","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"poi.business","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"poi.place_of_worship","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"poi.place_of_worship","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"geometry","stylers":[{"visibility":"simplified"}]},{"featureType":"water","stylers":[{"visibility":"on"},{"saturation":50},{"gamma":0},{"hue":"#50a5d1"}]},{"featureType":"administrative.neighborhood","elementType":"labels.text.fill","stylers":[{"color":"#333333"}]},{"featureType":"road.local","elementType":"labels.text","stylers":[{"weight":0.5},{"color":"#333333"}]},{"featureType":"transit.station","elementType":"labels.icon","stylers":[{"gamma":1},{"saturation":50}]}]
		  });

		  for(i = 0; i < potholes.length; i++){
				var marker = new google.maps.Marker({
					map: map,
					draggable: false,
					animation: google.maps.Animation.DROP,
					position: {
						lat: potholes[i].latitude,
						lng: potholes[i].longitude
					}
				})
				if(potholes[i].status == 0){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png')
				} else if(potholes[i].status == 1){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/yellow-dot.png')
				} else if(potholes[i].status == 2){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png')
				} else if(potholes[i].status == 3){
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png')
				} 
				
				var contentString = '<div style="width:250px">'+ "<span>Date Reported: </span>" + potholes[i].date_reported + "<br><br>";
				if(potholes[i].status == 0){
					contentString += "<span>Status: </span> Reported" + "<br><br>";
				} else if (potholes[i].status == 1){
					contentString += "<span>Status: </span> Confirmed" + "<br><br>";
				} else if (potholes[i].status == 2){
					contentString += "<span>Status: </span> Scheduled repair for " + potholes[i].date_scheduled_for_repair + "<br><br>";
				} else if (potholes[i].status == 3){
					contentString += "<span>Status: </span> Repair completed" + "<br><br>";
				}
				if(potholes[i].comments.length > 0){
					contentString += "<p>Comments: </p>" +potholes[i].comments;
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
	<c:import url="/WEB-INF/jsp/common/navbar.jsp" />
	
	<img alt="wee" class = "centerimg"src="img/pothole-tracker-logo1.png" style="width:80%;height:80%">

	<div id="map" class="col-md-12" style="height: 700px; margin-top: 2px; margin-bottom:50px;"></div>
	</div>
	

</body>
</html>