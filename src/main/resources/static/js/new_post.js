'use strict';

/* global google:true */

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
  
  function clearMessage() {
    error.removeChild(error.firstChild);
    success.removeChild(success.firstChild);
  }
  
  function showMessage(successMessage, errorMessage) {
    clearMessage();
    error.appendChild(document.createTextNode(errorMessage));
    success.appendChild(document.createTextNode(successMessage));
  }
  
  function init() {
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
    addAllInfo: addAllInfo
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
  // and adds the appropriate event listener
  function init() {
    var input = document.getElementById('pac-input');
    autocomplete = new google.maps.places.Autocomplete(input);
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
  
  function setMarkerInfo(info) {
    infoWindow.setContent(info);
  }
  
  function setMarkerPosition(latLng) {
    marker.setPosition(latLng);
  }
  
  function init() {
    var mapElement = document.getElementById('map');
    
    map = new google.maps.Map(mapElement, {
      center: {lat: 64.5, lng: -18.7},
      zoom: 6
    });
    
    geocoder = new google.maps.Geocoder();
    infoWindow = new google.maps.InfoWindow({ content: '' });
    
    marker = new google.maps.Marker({
      postion: null,
      map: map
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

function initProgram() {
  console.log("program");
  program.init();
  map.init();
  geolocation.init();
  autocomplete.init();
}