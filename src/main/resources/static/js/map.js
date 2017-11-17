'use strict';

/* global posts, google  */

// Javascript file for the front-page map

/*
var ICONS = {
  main: {
    icon: '/img/marker-dark.png',
    size: 16
  }
};
*/

var MAP_STYLES = [
    {
        "elementType": "labels",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
          "featureType": "road",
          "elementType": "labels",
          "stylers": [
              {
                  "visibility": "on"
              }
          ]
    },
    {
          "featureType": "road",
          "elementType": "labels.text.fill",
          "stylers": [
              {
                  "color": "#ffffff"
              }
          ]
    },
    {
        "elementType": "geometry",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "road",
        "elementType": "geometry",
        "stylers": [
            {
                "visibility": "on"
            },
            {
                "color": "#000000"
            }
        ]
    },
    {
        "featureType": "landscape",
        "stylers": [
            {
                "color": "#ffffff"
            },
            {
                "visibility": "on"
            }
        ]
    },
    {}
];

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
    
    console.log(post.archived);
    
    if (road) {
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
    console.log(posts);
    for (var i = 0; i < posts.length; i++) {
      var post = posts[i];
      var marker = new google.maps.Marker({
        position: new google.maps.LatLng(post.latitude, post.longitude),
        map: map
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
    var options = {
      zoom: 7, 
      center: null,
      styles: MAP_STYLES
    };
    console.log("init");
    console.log(posts.length);
    map = new google.maps.Map(document.getElementById('map'), options);
    infoWindow = new google.maps.InfoWindow;
    addMarkers();    
    specialListeners();
  }
  
  return {
    init: init
  };
})();
console.log(posts[1]);
console.log(posts);
// Initializes the map
function initMap() {
  program.init();
}



