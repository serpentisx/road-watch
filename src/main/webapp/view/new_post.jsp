<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<html lang="is">
  <head>
    <meta charset="utf-8">
    <title>Vegavaktin</title>
    <link rel="stylesheet" type="text/css" href="css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="<c:url value="css/main.css" />" rel="stylesheet">
    <link href="<c:url value="css/new_post.css" />" rel="stylesheet">
  </head>
  <body>
    
    <main>
      <jsp:include page="includes/nav.jsp" />
      <jsp:include page="includes/header.jsp" />
      <section>
        <div class="new-post-container">
          <form action="/new-post" method="POST">
            <fieldset>
              <legend>Nýtt innlegg</legend>

              <div class="title">
                <label for="title"></label>
                <input autofocus type="text" name="title" id="title" maxlength="25" required placeholder="Titill">
              </div>

              <div class="description">
                <label for="description"></label>
                <textarea name="description" id="description" rows="7" maxlength="150" placeholder="Stutt lýsing á annmarka" required></textarea>
              </div>

              <div class="location">
                <p>Ef þú ert um þessar mundir staddur við annmarkann geturðu notað staðsetningartækni til þess að staðsetja hann. Þú getur líka gert það með því að gefa upp kennileiti, t.d. heimilisfang eða nafn staðar.</p>

                <div class="loc-buttons">
                  <button type="button" class="coordinates">Staðsetningartækni</button>
                  <button type="button" class="landmark">Kennileiti</button>
                </div>

                <div class="enter-coordinates" style="display: none;">
                  <button type="button" class="generate-coordinates">
                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                    Sækja hnit
                  </button>
                </div>

                <div class="autocomplete-container" style="display: none;">
                  <label for="pac-input"></label>
                  <input id="pac-input" name="formatted_address" type="text" placeholder="Sláðu inn staðsetningu">
                </div>

                <div>
                  <span class="location-success-message"></span>
                  <span class="location-error-message"></span>
                </div>

                <div class="location-info">
                  <label>
                    <input name="latitude" type="text" disabled required title="" value="">
                    Breiddargráða
                  </label>
                  <label>
                    <input name="longitude" type="text" disabled required title="" value="">
                    Lengdargráða
                  </label>
                  <label>
                    <input name="road" type="text" disabled required title="" value="">
                    Vegheiti
                  </label>
                </div>
              </div>

              <div class="file-upload">
                <p id=file-input-desc">Veldu mynd:</p>
                <label for="file"></label>
                <!-- <i class="fa fa-upload" aria-hidden="true"></i> -->
                <input type="file" id="file" name="file" accept="image/*" aria-describedby="file-input-desc">
                <!-- should also validate the uploaded file on server side -->
                <!-- uploading image file is missing implementation in posts.js -->
              </div>

              <!-- Hidden inputs -->
              <input name="road_number" type="hidden" value="">
              <input name="zip" type="hidden" value="">
              <input name="locality" type="hidden" value="">

              <button class="submit" type="submit">Staðfesta</button>
            </fieldset>
          </form>
        </div>
      </section>
      
      <section class="map">
        <div class="map-wrapper">
          <div id="map"></div>
        </div>
      </section>
      <jsp:include page="includes/footer.jsp" />
    </main>
    
    <script src="<c:url value="js/new_post.js" />"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGz-droHcXcK93KzuCjzjrR7xr2QnQrjA&libraries=places&callback=initMap" async defer></script>
  </body>
</html>
