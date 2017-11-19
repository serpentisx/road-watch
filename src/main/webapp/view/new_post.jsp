<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<html lang="is">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Vegavaktin</title>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="<c:url value="/css/navigation.css" />" rel="stylesheet">
    <link href="<c:url value="/css/footer.css" />" rel="stylesheet">
    <link href="<c:url value="/css/new_post.css" />" rel="stylesheet">
  </head>
  <body>
    <jsp:include page="includes/nav.jsp" />    
    <main>
      <section class="np-container">
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
                <textarea name="description" id="description" rows="7" maxlength="150" placeholder="Stutt lýsing á annmarka" required style="margin-top: 0px; margin-bottom: 4px; height: 70px;"></textarea>
              </div>

              <div class="location">
                
                <p class="info">
                  Þú hefur þrjár leiðir til þess að gefa upp staðsetningu á annmarkanum:
                </p>
                <ol>
                  <li>Þú getur slegið inn nafn staðar eða heimilisfang og valið rétta niðurstöðu úr lista.</li>
                  <li>Þú getur valið staðsetningu af korti.</li>
                  <li>Einnig geturðu sótt hnit ef þú ert nú þegar á staðnum, en athugaðu að
                      sú aðferð gæti skilað ónákvæmri staðsetningu.</li>
                </ol>
                <p class="info">
                  Mundu bara að vera eins nákvæm(ur) og þú getur.
                </p>

                <div class="f-location">
                  <div class="autocomplete-container">
                    <label for="pac-input"></label>
                    <input id="pac-input" name="formatted_address" type="text" placeholder="Sláðu inn staðsetningu" required>
                  </div>

                  <div class="enter-coordinates">
                    <button class="generate-coordinates" type="button" data-toggle="tooltip" data-placement="top">
                      <i class="fa fa-map-marker" aria-hidden="true"></i>
                      Sækja hnit
                    </button>
                  </div>
                </div>

                <div class="message">
                  <span class="location-success-message"></span>
                  <span class="location-error-message"></span>
                </div>
              </div>

              <div class="file-upload">
                <label class="custom-file-upload" for="file">
                  <input id="file" type="file" name="file" accept="image/*">
                  <i class="fa fa-cloud-upload"></i>
                  <span class="file-name">Velja mynd</span>
                </label>
              </div>

              <!-- Hidden inputs -->
              <input name="latitude" type="hidden" value="">
              <input name="longitude" type="hidden" value="">
              <input name="road" type="hidden" value="">
              <input name="road_number" type="hidden" value="">
              <input name="zip" type="hidden" value="">
              <input name="locality" type="hidden" value="">

              <button class="submit" type="submit">Staðfesta</button>
              <p class="post-error-message">${message}</p>
            </fieldset>
          </form>
        </div>
        
        <div class="map-wrapper">
          <div id="map"></div>
        </div>
      </section>
    </main>
    <jsp:include page="includes/footer.jsp" />
    <script src="<c:url value="/js/new_post.js" />"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGz-droHcXcK93KzuCjzjrR7xr2QnQrjA&libraries=places&callback=initProgram" async defer></script>
  </body>
</html>
