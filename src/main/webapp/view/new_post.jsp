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
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous"> -->
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

                <div class="enter-coordinates">
                  <button class="generate-coordinates" type="button" data-toggle="tooltip" data-placement="top" title="Ef þú ert núna á staðnum geturðu notað staðsetningartækni til þess að gefa upp staðsetningu. Þú getur líka gert það með því að gefa upp kennileiti, t.d. heimilisfang eða nafn staðar. En reyndu þá að gefa upp eins nákvæma staðsetningu og þú getur.">
                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                    Sækja hnit
                  </button>
                </div>

                <div class="autocomplete-container">
                  <label for="pac-input"></label>
                  <input id="pac-input" name="formatted_address" type="text" placeholder="Sláðu inn staðsetningu" required>
                </div>

                <div>
                  <span class="location-success-message"></span>
                  <span class="location-error-message"></span>
                </div>
              </div>

              <div class="file-upload">
                <label for="file"></label>
                <input type="file" id="file" name="file" accept="image/*">
              </div>

              <!-- Hidden inputs -->
              <input name="latitude" type="hidden" value="">
              <input name="longitude" type="hidden" value="">
              <input name="road" type="hidden" value="">
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
    <!--
    <script src="<c:url value="https://code.jquery.com/jquery-3.2.1.slim.min.js" />" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" />" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" />" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    -->
    <script src="<c:url value="js/new_post.js" />"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGz-droHcXcK93KzuCjzjrR7xr2QnQrjA&libraries=places&callback=initMap" async defer></script>
  </body>
</html>
