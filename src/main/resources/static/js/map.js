'use strict';

/* global google:true posts:true */

function initMap() {
  // Location for markers
  
  const locations = [
    ['Miklabraut', 64.132059, -21.892566],
    ['Smáralind', 64.100894, -21.883619],
    ['Fríkirkjuvegur', 64.144857, -21.939334],
    ['Ikea', 64.074629, -21.918313],
    ['Bæjarháls', 64.120241, -21.800944],
    ['Sævarhöfði', 64.125718, -21.838354],
    ['Akranes', 64.319510, -22.072643],
    ['Borgarnes', 64.543574, -21.909673],
    ['Fitjar', 63.973296, -22.543399],
    ['Glerártorg', 65.687697, -18.102814],
    ['Hellisheiðarvirkjun', 64.037077, -21.401574],
    ['Hof', 65.684079, -18.088007],
    ['Selfoss', 63.943770, -21.008315],
    ['Turninn Kópavogur', 64.102370, -21.882014],
    ['Flatahraun Hafnarfirði', 64.075436, -21.950876],
    ['Smáralind bílastæði', 64.100844, -21.887777],
    ['Skógarlind Kópavogi', 64.101097, -21.873807],
    ['Kringlan - 2.hæð', 64.132575, -21.895456],
    ['Laugavegur 172-174 - Hekla', 64.141735, -21.901910],
    ['Suðurlandsbraut 2 - Nordica', 64.140100, -21.888829],
    ['Ármúli 2 - Lýsing hf.', 64.138791, -21.888315],
    ['Kjalarvogur 1 - Samskip', 64.138313, -21.842273],
    ['Nesjavellir', 64.116749, -21.251981],
    ['Tjarnargata - Ráðhús', 64.008202, -22.555034],
    ['Kaffi Egilsstaðir', 65.258822, -14.406742],
  ];

  // Construct a new map with a center position
  const map = new google.maps.Map(document.getElementById('map'), {
    zoom: 6,
    center: new google.maps.LatLng(65, -18.8),
  });

  // Construct a info window
  const infowindow = new google.maps.InfoWindow();

  // Construct markers
  let marker;
  for (let i = 0; i < locations.length; i++) {
    marker = new google.maps.Marker({
      position: new google.maps.LatLng(locations[i][1], locations[i][2]),
      map,
    });

    // Event handler for markers
    // Display repsectively information when the user clicks on a marker
    marker.addListener('click', ((marker, i) => {
      return () => {
        infowindow.setContent(locations[i][0]);
        infowindow.open(map, marker);
      };
    })(marker, i));
  }
}

initMap();
