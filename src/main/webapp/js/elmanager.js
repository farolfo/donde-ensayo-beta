var map;

function loadDetails(index, lat, lng, reference) {
	$('#showDetails_'+index).hide();
	$('#loading_'+index).fadeIn("fast");
	$('#placeDetails_'+index).fadeIn("fast");
	$('#hideDetails_'+index).fadeIn("fast");
	$('#placeDetails_'+index).load('details?reference='+reference+'&index='+index, function(){
		$('#loading_'+index).hide();
		var pyrmont = new google.maps.LatLng(lat,lng);
		  map = new google.maps.Map(document.getElementById('placeMap_'+index), {
		      mapTypeId: google.maps.MapTypeId.ROADMAP,
		      center: pyrmont,
		      zoom: 15
		    });

		  var request = {
		    reference: reference
		  };
		  var service = new google.maps.places.PlacesService(map);
		  service.getDetails(request, callback, map);
	});
}

function callback(place, status) {
	if (status == google.maps.places.PlacesServiceStatus.OK) {
	    createMarker(place);
	 }
}

function createMarker(place) {
    var placeLoc = place.geometry.location;
    var marker = new google.maps.Marker({
      map: map,
      position: place.geometry.location
    });

    google.maps.event.addListener(marker, 'click', function() {
      infowindow.setContent(place.name);
      infowindow.open(map, this);
    });
  }

function hideDetails(index){
	$('#showDetails_'+index).fadeIn("fast");
	$('#placeDetails_'+index).fadeOut("fast");
	$('#hideDetails_'+index).fadeOut("fast");
}

function displayLikes(index, hasFacebookLikes){
	alert(hasFacebookLikes);
	if(hasFacebookLikes){
		$('#likes_'+index).show();
	}
}


$(document).ready(function() {
	$(".positiveRestricted").numeric({ decimal: false, negative: false }, function() { this.value = ""; this.focus(); });
	$(".positiveRestrictedDouble").numeric({ negative: false }, function() { this.value = ""; this.focus(); });
});