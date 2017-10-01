'use strict';

/* global google:true */

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

// Determines if search results includes road of place searched for.
// If road exists, add it to the new post form; if road is not found, return false.
function findRoadOfSearchResult(components) {
  for (var i = 0; i < components.length; i++) {
    for (var j = 0; j < components[i].types; j++) {
      if (components[i].types[j] === "route") {
        addRoadInfo(components);
        return true;
      }
    }
  }
  return false;
}

// Handles event when user selects a place from the autocomplete search box
function placeChangedHandler(autocomplete, e) {
  var place = autocomplete.getPlace();
  var messageEl = document.querySelector('.location-error-message');
  if (!place.geometry) {
    // User entered the name of a Place that was not suggested and
    // pressed the Enter key, or the Place Details request failed.
    messageEl.innerHTML = "Staðsetning finnst ekki, reyndu aftur";
    return;
  }
  // If route/road is found for place, add the coordinates, otherwise display error message
  if (addRoadOfSearchResult(place.address_components)) {
    addPlaceCoordinates(place.geometry.location);
  } else {
    messageEl.innerHTML = "Enginn vegur finnst fyrir staðsetningu, reyndu aftur";
  }
}

// Callback function defined in Google Maps <script> tag.
// Initializes autocomplete place search in new post form.
function initAutocompletePlaceSearch() {
  var input = document.getElementById('autocomplete-place-search');
  var autocomplete = new google.maps.places.Autocomplete(input);
  autocomplete.addListener('place_changed', placeChangedHandler.bind(null, autocomplete));
}