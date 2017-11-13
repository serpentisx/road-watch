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
    <link rel="stylesheet" type="text/css" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="<c:url value="/css/navigation.css" />" rel="stylesheet">
    <link href="<c:url value="/css/footer.css" />" rel="stylesheet">
    <link href="<c:url value="/css/new_post.css" />" rel="stylesheet">
  </head>
  <body>    
    <main>
      <jsp:include page="includes/nav.jsp" />
      
      <section>
        <div class="new-post-container">
          <form action="/innlegg" method="POST" enctype="multipart/form-data">
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
                <i class="fa fa-file-image-o" aria-hidden="true"></i>
                <label for="file"></label>
                <input id="file" type="file" name="file" accept="image/*">
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
          <p class="post-error-message">${message}</p>
        </div>
      </section>
      
      <section class="map">
        <div class="map-wrapper">
          <div id="map"></div>
        </div>
      </section>
        
      <jsp:include page="includes/footer.jsp" />
    </main>
    <script src="<c:url value="/js/new_post.js" />"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGz-droHcXcK93KzuCjzjrR7xr2QnQrjA&libraries=places&callback=initProgram" async defer></script>
  </body>
</html>
