'use strict';

/* global google:true */

/* functions for adding info to new post form */

function addPlaceCoordinates(location) {
  var latitudeInput = document.querySelector('input[name=latitude]');
  var longitudeInput = document.querySelector('input[name=longitude]');
  latitudeInput.value = location.lat;
  longitudeInput.value = location.lng;
}

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
function addRoadOfSearchResult(components) {
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

function addAllInfo(location) {
  var messageError = document.querySelector('.location-error-message');
  var messageSuccess = document.querySelector('.location-success-message');
  // If route/road is found for place, add the coordinates, otherwise display error message
  var roadFound = addRoadOfSearchResult(location.address_components);
  if (roadFound) {
    addPlaceCoordinates(location.geometry.location);
    messageSuccess.innerHTML = "Tókst að finna veg!";
  } else {
    messageError.innerHTML = "Enginn vegur finnst fyrir staðsetningu, reyndu aftur";
  }
}


/* Autocomplete functions */

// Handles event when user selects a place from the autocomplete search box
function placeChangedHandler(autocomplete, e) {
  var place = autocomplete.getPlace();
  if (!place.geometry) {
    // User entered the name of a Place that was not suggested and
    // pressed the Enter key, or the Place Details request failed.
    var messageError = document.querySelector('.location-error-message');
    messageError.innerHTML = "Staðsetning finnst ekki, reyndu aftur";
    return;
  }
  console.log(place.address_components);
  addAllInfo(place);
}

// Callback function defined in Google Maps <script> tag.
// Initializes autocomplete place search in new post form.
function initAutocompletePlaceSearch() {
  var input = document.getElementById('autocomplete-place-search');
  var autocomplete = new google.maps.places.Autocomplete(input);
  autocomplete.addListener('place_changed', placeChangedHandler.bind(null, autocomplete));
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
    navigator.geolocation.getCurrentPosition(addPosition);
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


/* New post functions */

// Event handler for new post buttons
function toggleNewPostView() {
  var newPostContainer = document.querySelector('.new-post-container');
  var posts = document.querySelector('.posts');
  var button = document.querySelector('button.fixed');
  if (!newPostContainer.style.display || newPostContainer.style.display === 'none') {
    newPostContainer.style.display = 'flex';
    posts.style.display = 'none';
    button.style.display = 'none';
  } else {
    newPostContainer.style.display = 'none';
    posts.style.display = 'flex';
    button.style.display = 'block';
  }
}

// Adds event listeners to two buttons that toggle the new post form
// One of the buttons opens the form and the other closes it.
function addNewPostListeners() {
  var buttons = document.querySelectorAll('.new-post-toggle');
  for (var i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener('click', toggleNewPostView, false);
  }
}

// Event handler deciding course of action when button for requesting
// to enter GPS location information of new post is clicked by user
function toggleCoordinatesView() {
  var coordinatesContainer = document.querySelector('.enter-coordinates');
  var landmarkContainer = document.querySelector('.enter-landmark');
  if (coordinatesContainer.style.display === 'none') {
    coordinatesContainer.style.display = 'block';
    landmarkContainer.style.display = 'none';
  }
}

// Event handler deciding course of action when button for requesting
// to enter landmark location information of new post is clicked by user
function toggleLandmarkView() {
  var coordinatesContainer = document.querySelector('.enter-coordinates');
  var landmarkContainer = document.querySelector('.enter-landmark');
  if (landmarkContainer.style.display === 'none') {
    coordinatesContainer.style.display = 'none';
    landmarkContainer.style.display = 'block';
  }  
}

// Adds event listeners to two buttons that allow the user to pick
// which method he wants to use for determining location of road system defect
// within the new post form
function addLocationListeners() {
  var coordinatesButton = document.querySelector('.coordinates');
  var landmarkButton = document.querySelector('.landmark');
  coordinatesButton.addEventListener('click', toggleCoordinatesView, false);
  landmarkButton.addEventListener('click', toggleLandmarkView, false);
}


/* Photo upload functions */

function handleFileSelect(evt) {
  // Appropriate implementation missing
  // Remember to validate file on server side
  // See section "Reading files" at following url or look for other sources
  // https://www.html5rocks.com/en/tutorials/file/dndfiles/
    }
  
function addUploadPhotoListener() {
  var fileInput = document.getElementById('file');
  fileInput.addEventListener('change', handleFileSelect, false);
}

document.addEventListener('DOMContentLoaded', function () {
  addNewPostListeners();
  addLocationListeners();
  addCoordinatesGeneratorListener();
  addUploadPhotoListener();
});

