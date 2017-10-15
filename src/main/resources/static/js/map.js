'use strict';

$('.mobile-label').click(function () {
    if ($('#mobile-check').prop('checked')) {
        setTimeout(function () {
            $('.map-container').toggleClass('hide');
        }, 400);
    } 
});

$('.map-label').click(function () {
   $('.map-container').toggleClass('hide');
 
   setTimeout(function () {
              $('#mobile-check').prop('checked', true);
        }, 10);
});

/* global posts, google  */

function initMap() {
  // Location for markers

  // Construct a new map with a center position
  const map = new google.maps.Map(document.getElementById('map'), {
    zoom: 6,
    center: new google.maps.LatLng(65, -18.8)
  });
  
  // Loop over all posts and add markers
  let marker;
  let info;
  let post;
  let road;
  let infowindow = new google.maps.InfoWindow;
  for (var i = 0; i < posts.length; i++) {
    post = posts[i];
    console.log(post);
     marker = new google.maps.Marker({
      position: new google.maps.LatLng(post.latitude, post.longitude),
      map
    });
    
    road = post.road;
    
    info = "<b>" + post.title + "</b>";
    if (road) {
      info += ("<br>" + road.name);
      if (road.roadNumber)     info += (" (vegnr. " + road.roadNumber + ")");
      if (road.locality)      info += ("<br>" + road.locality);
      if (road.municipality)  info += ("<br>" + road.municipality);
      if (road.regionIS)      info += ("<br>" + road.regionIS);
    }
     // Event handler for markers
    // Display repsectively information when the user clicks on a marker
    marker.addListener('click', ((marker, info) => {
      return () => {
        infowindow.setContent(info);
        infowindow.open(map, marker);
      };
    })(marker, info));
  }
}

initMap();
