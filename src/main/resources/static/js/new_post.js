'use strict';

/* global google:true */

var map;
var mapMarker;
var infowindow;

/* General functions for adding info to new post form and displaying messages to the user */

// Displays message to the user
function displayMessage (success, error) {
  var messageError = document.querySelector('.location-error-message');
  var messageSuccess = document.querySelector('.location-success-message');
  messageError.innerHTML = error;
  messageSuccess.innerHTML = success;
}

// Sets the marker on the map to the selected location
function setMarker(coordinates) {
  mapMarker.setPosition(coordinates);
}

// Adds relevant location info to the new post form and displays message
function addPlaceInfo(location) {
  var addressInput = document.getElementById('pac-input');
  var latitudeInput = document.querySelector('input[name=latitude]');
  var longitudeInput = document.querySelector('input[name=longitude]');
  var roadInput = document.querySelector('input[name=road]');
  var lat = location.geometry.location.lat();
  var lng = location.geometry.location.lng();
  latitudeInput.value = lat;
  longitudeInput.value = lng;
  addressInput.value = location.formatted_address;
  displayMessage(roadInput.value + " (" + lat.toFixed(4) + ", " + lng.toFixed(4) + ")", "");
}

// Adds available road info to the new post form
function addRoadInfo(components) {
  for (var i = 0; i < components.length; i++) {
    var component = components[i];
    
    if (component.types[0] === "route") {
      var roadInput = document.querySelector('input[name=road]');
      roadInput.value = component.long_name;
      
      // athugum hvort leitin skili vegnúmeri (sem er heiltala eða byrjar á "F"):
      if (parseInt(component.short_name) || parseInt(component.short_name.slice(1))) {
        var roadNumInput = document.querySelector('input[name=road_number]');
        roadNumInput.value = component.short_name;
      }
      
    } else if (component.types[0] === "locality") {
      var localityInput = document.querySelector('input[name=locality]');
      localityInput.value = component.long_name;
      
    } else if (component.types[0] === "postal_code") {
      var zipInput = document.querySelector('input[name=zip]');
      zipInput.value = component.long_name;
      
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
        infowindow.setContent(components[i].long_name);
        return true;
      }
    }
  }
  return false;
}

// Adds all the information of a place search result
// to the new post form and handles potential errors
function addAllInfo(location) {
  // If route/road is found for place, add the coordinates, otherwise display error message
  var roadFound = findRoadOfSearchResult(location.address_components);
  if (roadFound) {
    addPlaceInfo(location);
  } else {
    displayMessage("", "Enginn vegur finnst fyrir staðsetningu, reyndu aftur");
  }
  return roadFound;
}


/* Autocomplete functions */

// Handles event when user selects a place from the autocomplete input
function placeChangedHandler(autocomplete, e) {
  var place = autocomplete.getPlace();
  if (!place.geometry) {
    // User entered the name of a Place that was not suggested and
    // pressed the Enter key, or the Place Details request failed.
    var messageError = document.querySelector('.location-error-message');
    messageError.innerHTML = "Staðsetning finnst ekki, reyndu aftur";
    return;
  }
  addAllInfo(place);
}

// Initializes Google Autocomplete, ties to input element
// and adds the appropriate event listener
function initAutoComplete() {
  var input = document.getElementById('pac-input');
  var autocomplete = new google.maps.places.Autocomplete(input);
  autocomplete.addListener('place_changed', placeChangedHandler.bind(null, autocomplete));
}

// Callback function defined in Google Maps <script> tag.
// Initializes map, adds click event listener,
// and initializes autocomplete place search in new post form.
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 64.5, lng: -18.7},
    zoom: 6
  });
  
  mapMarker = new google.maps.Marker({
    postion: null,
    map: map
  });
  
  infowindow = new google.maps.InfoWindow({ content: '' });
  
  mapMarker.addListener('click', function() {
    infowindow.open(map, mapMarker);
  });
  
  map.addListener('click', function(e) {
    var coords = e.latLng;
    
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({'location': coords}, function(results, status) {
      if (status === 'OK') {
        if (addAllInfo(results[0])) setMarker(coords);
      } else {
        displayMessage("", "Óþekkt villa. Ekki tókst að sækja staðsetninguna");
      }
    });
  });
    
  initAutoComplete();
}


/* Coordinates generation function */

// Uses Google Geocoder to obtain the user's location
// and adds all the info to the form
var addPosition = function (position) {
  var geocoder = new google.maps.Geocoder();
  var latlng = {lat: position.coords.latitude, lng: position.coords.longitude};
  geocoder.geocode({'location': latlng}, function(results, status) {
    if (status === 'OK') {
      addAllInfo(results[0]);
    } else {
      displayMessage("", "Óþekkt villa. Ekki tókst að sækja staðsetninguna");
    }
  });
};

// Geolocation error function
var geoError = function (error) {
  console.log('Geolocation error occurred. Error code: ' + error.code);
  switch(error.code) {
    case 1:
      displayMessage("", "Aðgangur að staðsetningarþjónustu hindraður, ekki tókst að sækja staðsetningu.");
      break;
    case 2:
      displayMessage("", "Staðsetningarþjónusta skilaði villu, ekki tókst að sækja staðsetningu.");
      break;
    case 3:
      displayMessage("", "Staðsetningarþjónusta rann út á tíma, ekki tókst að sækja staðsetningu.");
      break;
    default: // unknown error, code: 0
      displayMessage("", "Óþekkt villa, ekki tókst að sækja staðsetningu.");
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
    displayMessage("", "Vafrinn styður ekki staðsetningartækni");
  }
}

// Adds event listener to button that generates geographical 
// coordinates of road system defect
function addCoordinatesGeneratorListener() {
  var button = document.querySelector('.generate-coordinates');
  button.addEventListener('click', generateCoordinates, false);
}


/* Photo upload functions */

// Handles user file selection
function handleFileSelect(evt) {
  // Appropriate implementation missing
  // Remember to validate file on server side
  // See section "Reading files" at following url or look for other sources
  // https://www.html5rocks.com/en/tutorials/file/dndfiles/
}

// Adds an event listener to the file upload input
function addUploadPhotoListener() {
  var fileInput = document.getElementById('file');
  fileInput.addEventListener('change', handleFileSelect, false);
}

/* Fires when DOM content has been loaded */
document.addEventListener('DOMContentLoaded', function () {
  addCoordinatesGeneratorListener();
  addUploadPhotoListener();
  // $('[data-toggle="tooltip"]').tooltip()
});

