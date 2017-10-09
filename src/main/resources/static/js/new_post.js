'use strict';

/* global google:true */

var map;
var mapMarker;

/* General functions for adding info to new post form and displaying messages to the user */

// Displays message to the user
function displayMessage (success, error) {
  var messageError = document.querySelector('.location-error-message');
  var messageSuccess = document.querySelector('.location-success-message');
  messageError.innerHTML = error;
  messageSuccess.innerHTML = success;
}

// Sets the marker on the map to the selected location
function setMarker(location) {
  if (mapMarker) { mapMarker.setMap(null); }
  mapMarker = new google.maps.Marker({
    position: new google.maps.LatLng(location.lat(), location.lng()),
    map: map
  });
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
      if (components[i].types[j] === "route") {
        addRoadInfo(components);
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
    setMarker(location.geometry.location);
  } else {
    displayMessage("", "Enginn vegur finnst fyrir staðsetningu, reyndu aftur");
  }
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
// Initializes map and autocomplete place search in new post form.
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 64.5, lng: -18.7},
    zoom: 6
  });
  initAutoComplete();
}


/* Coordinates generation function */

// Adds the geographical coordinates to the new post form
// if a corresponding road is found
function addPosition(position) {
  var geocoder = new google.maps.Geocoder();
  var latlng = {lat: position.coords.latitude, lng: position.coords.longitude};
  geocoder.geocode({'location': latlng}, function(results, status) {
    addAllInfo(results[0]);
  });
}

// Event handler for coordinates generation
function generateCoordinates() {
  var successMessage = document.querySelector('.location-success-message');
  var errorMessage = document.querySelector('.location-error-message');
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      addPosition, 
      function error(msg){
        alert('Please enable your GPS.');
      },
      { maximumAge: 600000, timeout: 5000, enableHighAccuracy: true }
    );
  } else {
    errorMessage.innerHTML = "Vafrinn styður ekki staðsetningartækni";
    successMessage.innerHTML = "";
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

