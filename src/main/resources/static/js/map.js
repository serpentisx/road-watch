'use strict';

/* global posts, google  */

var program = (function() {
  var map;
  var infoWindow;
  
  function addMarkerListener(marker, info) {
    marker.addListener('click', ((marker, info) => {
      return () => {
        infoWindow.setContent(info);
        infoWindow.open(map, marker);
      };
    })(marker, info));
  }
  
  function getPostInfo(post) {
    var road = post.road;

    var info = "<b>" + post.title + "</b>";
    if (road) {
      info += ("<br>" + road.name);
      if (road.roadNumber)     info += (" (vegnr. " + road.roadNumber + ")");
      if (road.locality)      info += ("<br>" + road.locality);
      if (road.municipality)  info += ("<br>" + road.municipality);
      if (road.regionIS)      info += ("<br>" + road.regionIS);
    }
    return info;
  }
  
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

function initMap() {
  program.init();
}


