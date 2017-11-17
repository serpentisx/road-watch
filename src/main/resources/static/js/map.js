'use strict';

/* global posts, google  */

// Javascript file for Map

var program = (function() {
  var map;
  var infoWindow;
  
  // Add a marker listener
  // @param marker: the marker to add an listener to
  // @param info: the info to be displayed when an event is fired
  function addMarkerListener(marker, info) {
    marker.addListener('click', (function (marker, info) {
      return function() {
        infoWindow.setContent(info);
        infoWindow.open(map, marker);
      };
    })(marker, info));
  }
  
  // Get post information and pretty-format it
  // @param post: the post to get information from
  function getPostInfo(post) {
    var road = post.road;

    var info = "<b>" + post.title + "</b><br>Dags. " + post.date;
    
    if (!post.archived && road) {
      info += ("<br>" + road.name);
      if (road.roadNumber)     info += (" (vegnr. " + road.roadNumber + ")");
      if (road.locality)      info += ("<br>" + road.locality);
      if (road.municipality)  info += ("<br>" + road.municipality);
      if (road.regionIS)      info += ("<br>" + road.regionIS);
    }
    return info;
  }
  
  // Add markers to the map
  function addMarkers() {
    for (var i = 0; i < posts.length; i++) {
      var post = posts[i];
      
      var marker = new google.maps.Marker({
        position: new google.maps.LatLng(post.latitude, post.longitude),
        map
      });
      
      addMarkerListener(marker, getPostInfo(post));
    }
  }
  
  // Event listeners to toggle the map
  function specialListeners() {
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
        google.maps.event.trigger(map, "resize");
        map.setCenter(new google.maps.LatLng(65, -18.8));
      }, 10);
    });
  }
  
  // Initializes the map
  function init() {    
    map = new google.maps.Map(document.getElementById('map'), {zoom: 6, center: null});
    infoWindow = new google.maps.InfoWindow;
    addMarkers();    
    specialListeners();
  }
  
  return {
    init: init
  };
})();

// Initializes the map
function initMap() {
  program.init();
}


