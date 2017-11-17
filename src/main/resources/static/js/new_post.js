'use strict';

/* global google:true */

// JavaScript for the new-post page

var ICONS = {
  main: {
    icon: '/img/marker.png'
  }
};

var MAP_STYLES = [
    {
        "featureType": "all",
        "elementType": "all",
        "stylers": [
            {
                "visibility": "on"
            }
        ]
    },
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
                  "color": "#000000"
              }
          ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#000000"
            },
            {
                "lightness": 20
            }
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#000000"
            },
            {
                "lightness": 17
            },
            {
                "weight": 1.2
            }
        ]
    },
    {
        "featureType": "landscape",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#000000"
            },
            {
                "lightness": 20
            }
        ]
    },
    {
        "featureType": "landscape",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#4d6059"
            }
        ]
    },
    {
        "featureType": "landscape",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#4d6059"
            }
        ]
    },
    {
        "featureType": "landscape.natural",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#4d6059"
            }
        ]
    },
    {
        "featureType": "poi",
        "elementType": "geometry",
        "stylers": [
            {
                "lightness": 21
            }
        ]
    },
    {
        "featureType": "poi",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#4d6059"
            }
        ]
    },
    {
        "featureType": "poi",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#4d6059"
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
                "color": "#7f8d89"
            }
        ]
    },
    {
        "featureType": "road",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#7f8d89"
            }
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#7f8d89"
            },
            {
                "lightness": 17
            }
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#7f8d89"
            },
            {
                "lightness": 29
            },
            {
                "weight": 0.2
            }
        ]
    },
    {
        "featureType": "road.arterial",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#000000"
            },
            {
                "lightness": 18
            }
        ]
    },
    {
        "featureType": "road.arterial",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#7f8d89"
            }
        ]
    },
    {
        "featureType": "road.arterial",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#7f8d89"
            }
        ]
    },
    {
        "featureType": "road.local",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#000000"
            },
            {
                "lightness": 16
            }
        ]
    },
    {
        "featureType": "road.local",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#7f8d89"
            }
        ]
    },
    {
        "featureType": "road.local",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#7f8d89"
            }
        ]
    },
    {
        "featureType": "transit",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#000000"
            },
            {
                "lightness": 19
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "all",
        "stylers": [
            {
                "color": "#2b3638"
            },
            {
                "visibility": "on"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#2b3638"
            },
            {
                "lightness": 17
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#24282b"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#24282b"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels.text",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels.text.fill",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels.text.stroke",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels.icon",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    }
];

/*****************/
/* Shared Module */
/*****************/
  
/* Functions for adding info to new post form and displaying messages to the user */
var program = (function() {
  var error;
  var success;
  var address;
  var latitude;
  var longitude;
  var road;
  var roadNumber;
  var locality;
  var zip;
  
  // Add event listener on change for file upload
  function addFileSelectListener() {
    document.getElementById('file').addEventListener('change', function (e) {
       document.querySelector('.file-name').textContent = this.value;
    });
  }
  
  // Adds relevant location info to the new post form and displays message
  function addPlaceInfo(location) {
    var roadInput = document.querySelector('input[name=road]');
    var lat = location.geometry.location.lat();
    var lng = location.geometry.location.lng();
    latitude.value = lat;
    longitude.value = lng;
    address.value = location.formatted_address;
    showMessage(roadInput.value + " (" + lat.toFixed(4) + ", " + lng.toFixed(4) + ")", "");
    return new google.maps.LatLng(lat, lng);
  }

  // Adds available road info to the new post form
  function addRoadInfo(components) {
    for (var i = 0; i < components.length; i++) {
      var component = components[i];

      if (component.types[0] === "route") {
        road.value = component.long_name;

        // athugum hvort leitin skili vegnúmeri (sem er heiltala eða byrjar á "F"):
        if (parseInt(component.short_name) || parseInt(component.short_name.slice(1))) {
          roadNumber.value = component.short_name;
        }

      } else if (component.types[0] === "locality") {
        locality.value = component.long_name;

      } else if (component.types[0] === "postal_code") {
        zip.value = component.long_name;

      } else {
        continue;
      }
    }
  }

  // Determines the road that corresponds to the generated coordinates
  // and if results include road of place searched for, adds it to the new post form.
  // If the road is not found, returns false.
  function findRoadOfSearchResult(components) {
    for (var i = 0; i < components.length; i++) {
      for (var j = 0; j < components[i].types.length; j++) {
        if (components[i].types[j] === "route" && components[i].long_name !== 'Unnamed Road') {
          addRoadInfo(components);
          map.setInfo(components[i].long_name);
          return true;
        }
      }
    }
    return false;
  }

  // Adds all the information of a place search result
  // to the new post form and handles potential errors
  // If route/road is found for place, add the coordinates, otherwise display error message
  function addAllInfo(location) {
    var roadFound = findRoadOfSearchResult(location.address_components);
    if (roadFound) {
      var googleLatLng = addPlaceInfo(location);
      map.setMarkerPosition(googleLatLng);
    } else {
      showMessage("", "Enginn vegur finnst fyrir staðsetningu, reyndu aftur");
    }
    return roadFound;
  }
  
  // Clears the displayed message on the screen
  function clearMessage() {
    error.removeChild(error.firstChild);
    success.removeChild(success.firstChild);
  }
  
  // Shows messages on the screen
  // @param successMessage: the success message to show
  // @param errorMessage: the errorMessage to show 
  function showMessage(successMessage, errorMessage) {
    clearMessage();
    error.appendChild(document.createTextNode(errorMessage));
    success.appendChild(document.createTextNode(successMessage));
  }
  
  // Fetch information for all input fields
  function init() {
    addFileSelectListener();
        
    error = document.querySelector('.location-error-message');
    success = document.querySelector('.location-success-message');
    error.appendChild(document.createTextNode(''));
    success.appendChild(document.createTextNode(''));
    
    address = document.getElementById('pac-input');
    latitude = document.querySelector('input[name=latitude]');
    longitude = document.querySelector('input[name=longitude]');
    road = document.querySelector('input[name=road]');
    roadNumber = document.querySelector('input[name=road_number]');
    locality = document.querySelector('input[name=locality]');
    zip = document.querySelector('input[name=zip]');
  }
  
  return {
    init: init,
    showMessage: showMessage,
    addAllInfo: addAllInfo,
    addFileSelectListener: addFileSelectListener
  };
})();


/***********************/
/* Autocomplete Module */
/***********************/

var autocomplete = (function() {
  var autocomplete;
  
  // Handles event when user selects a place from the autocomplete input
  function placeChangedHandler() {
    var place = autocomplete.getPlace();
    if (!place.geometry) {
      // User entered the name of a Place that was not suggested and
      // pressed the Enter key, or the Place Details request failed.
      program.showMessage("", "Staðsetning finnst ekki, reyndu aftur");
    } 
    else {
      program.addAllInfo(place);
    }
  }
  
  // Initializes Google Autocomplete, ties to input element,
  // and adds the appropriate event listener. Restricts suggestions to Iceland.
  function init() {
    var input = document.getElementById('pac-input');
    var options = {
      componentRestrictions: {country: 'is'}
    };
    autocomplete = new google.maps.places.Autocomplete(input, options);
    autocomplete.addListener('place_changed', placeChangedHandler);
  }
  
  return {
    init: init
  };
})();


/**************/
/* Map Module */
/**************/

var map = (function() {
  var map;
  var geocoder;
  var infoWindow;
  var marker;
  
  // Mark a position on map and get its coordinates
  function mapOnClick(e) {
    var coords = e.latLng;
    
    geocoder.geocode({'location': coords}, function(results, status) {
      if (status === 'OK') {
        if (program.addAllInfo(results[0])) { setMarkerPosition(coords); }
      } else {
        program.showMessage("", "Óþekkt villa. Ekki tókst að sækja staðsetninguna");
      }
    });
  }
  
  // Set a marker's details
  // @param info: the details to set to the marker
  function setMarkerInfo(info) {
    infoWindow.setContent(info);
  }
  
  // Set a marker's position
  // @param latLang: coordinates (latitude, longitude) of the marker
  function setMarkerPosition(latLng) {
    marker.setPosition(latLng);
  }
  
  // Initializes the map
  function init() {
    var mapElement = document.getElementById('map');
    
    map = new google.maps.Map(mapElement, {
      center: {lat: 64.5, lng: -18.7},
      zoom: 6,
      styles: MAP_STYLES
    });
    
    geocoder = new google.maps.Geocoder();
    infoWindow = new google.maps.InfoWindow({ content: '' });
    
    marker = new google.maps.Marker({
      postion: null,
      map: map
    });
    
    marker.setIcon({
      url: ICONS.main.icon
    });
    
    marker.addListener('click', function() {
      infoWindow.open(map, marker);
    });
    
    map.addListener('click', mapOnClick);
  }
  
  return {
    init: init,
    setInfo: setMarkerInfo,
    setMarkerPosition: setMarkerPosition
  };
})();


/**********************/
/* Geolocation Module */
/**********************/

var geolocation = (function() {
  var geocoder;
  
  // Add a position to gelocation
  // @param position: the position to be added
  var addPosition = function (position) {
    var latlng = {lat: position.coords.latitude, lng: position.coords.longitude};
    geocoder.geocode({'location': latlng}, function(results, status) {
      if (status === 'OK') {
        program.addAllInfo(results[0]);
      } else {
        program.showMessage("", "Óþekkt villa. Ekki tókst að sækja staðsetninguna");
      }
    });
  };
  
  // Geolocation error function
  var geoError = function (error) {
    console.log('Geolocation error occurred. Error code: ' + error.code);
    switch(error.code) {
      case 1:
        program.showMessage("", "Aðgangur að staðsetningarþjónustu hindraður, ekki tókst að sækja staðsetningu.");
        break;
      case 2:
        program.showMessage("", "Staðsetningarþjónusta skilaði villu, ekki tókst að sækja staðsetningu.");
        break;
      case 3:
        program.showMessage("", "Staðsetningarþjónusta rann út á tíma, ekki tókst að sækja staðsetningu.");
        break;
      default: // unknown error, code: 0
        program.showMessage("", "Óþekkt villa, ekki tókst að sækja staðsetningu.");
    }
  };
  
  // Event handler for coordinates generation
  function generateCoordinates() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        addPosition, 
        geoError,
        { timeout: 10000, enableHighAccuracy: true }
      );
    } else {
      program.showMessage("", "Vafrinn styður ekki staðsetningartækni");
    }
  }
  
  // Adds event listener to button that generates geographical 
  // coordinates of road system defect
  function init() {
    geocoder = new google.maps.Geocoder();
    
    var button = document.querySelector('.generate-coordinates');
    button.addEventListener('click', generateCoordinates, false);
  }
  
  return {
    init: init
  };
})();

// Fire everything off
function initProgram() {
  program.init();
  map.init();
  geolocation.init();
  autocomplete.init();
}