'use strict';

/* global google:true */

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

// Determines the road that corresponds to the generated coordinates
// and if the road is not found, prompts the user to try again
function findRoad(position) {
  var geocoder = new google.maps.Geocoder();
  // Appropriate implementation missing
  // Need to: Find the road with Maps Geocoder, and if found,
  // add its name to form input element with name="road", and other
  // information to other input elements, such as zip and locality. 
  // Finally, display success message
  // (see <span class="location-success message"> ).
  // If the road is not found, then display error message
  // (see <span class="location-error-message"> ).
  // Remember to return true if road was found, and false otherwise.
  
  return false;
}

// Adds the geographical coordinates to the new post form
// if a corresponding road is found
function addPosition(position) {
  var roadFound = findRoad(position);
  
  if (roadFound) {
    var latitudeInput = document.querySelector('input[name=latitude]');
    var longitudeInput = document.querySelector('input[name=longitude]');
    latitudeInput.value = position.coords.latitude;
    longitudeInput.value = position.coords.longitude;
  }
  
  console.log(position.coords);
  console.log(position.coords.accuracy);
}

// Event handler for coordinates generation
function generateCoordinates() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(addPosition);
    var messageElement = document.querySelector('.geolocation-success-message');
    messageElement.innerHTML = "Tókst að sækja staðsetningu!";
  } else {
    var messageElement = document.querySelector('.geolocation-error-message');
    messageElement.innerHTML = "Vafrinn styður ekki staðsetningartækni";
  }
}

// Adds event listener to button that generates geographical 
// coordinates of road system defect
function addCoordinatesGeneratorListener() {
  var button = document.querySelector('.generate-coordinates');
  button.addEventListener('click', generateCoordinates, false);
}

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

