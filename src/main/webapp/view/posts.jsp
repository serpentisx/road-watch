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
    <link href="<c:url value="css/font-awesome-4.7.0/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="css/posts.css" />" rel="stylesheet">
  </head>
  <body>
    <jsp:include page="includes/nav.jsp" />
    <main>
      <div class="create-new-post">
        <button class="fixed new-post-toggle" type="button">Nýtt innlegg</button>
        
        <div class="new-post-container">
          <form action="/new-post" method="POST">
            <fieldset>
              <legend>Nýtt innlegg</legend>

              <div class="title">
                <label for="title">Titill</label>
                <input autofocus type="text" name="title" id="title" maxlength="25" required placeholder="Titill">
              </div>
              
              <div class="description">
                <label for="description">Stutt lýsing</label>
                <textarea name="description" id="description" cols="42" rows="6" maxlength="150" required></textarea>
              </div>

              <div class="location">
                <p>Ef þú ert um þessar mundir staddur við annmarkann geturðu notað staðsetningartækni til þess að staðsetja hann. Þú getur líka gert það með því að gefa upp kennileiti, t.d. heimilisfang eða nafn staðar.</p>
                <button type="button" class="coordinates">Staðsetningartækni</button>
                <button type="button" class="landmark">Kennileiti</button>
                
                <div class="enter-coordinates" style="display: none;">
                  <button type="button" class="generate-coordinates">
                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                  </button>
                </div>
                
                <div class="enter-landmark" style="display: none;">
                  <label for="autocomplete-place-search"></label>
                  <input id="autocomplete-place-search" name="formatted_address" type="text" placeholder="Sláðu inn staðsetningu">
                </div>
                
                <span class="location-success-message"></span>
                <span class="location-error-message"></span>
                
                <div class="position">
                  <label>
                    Breiddargráða:
                    <input name="latitude" type="number" disabled required value="">
                  </label>
                  <label>
                    Lengdargráða:
                    <input name="longitude" type="number" disabled required value="">
                  </label>
                  <label>
                    Vegur:
                    <input name="road" type="text" disabled required value="">
                  </label>
                </div>
              </div>

              <div class="file-upload">
                <label for="file"></label>
                <!-- <i class="fa fa-upload" aria-hidden="true"></i> -->
                <input type="file" id="file" name="file" accept="image/*">
                <!-- should also validate the uploaded file on server side -->
                <!-- uploading image file is missing implementation in posts.js -->
              </div>
              
              <!-- Hidden inputs -->
              <input name="road_number" type="hidden" value="">
              <input name="zip" type="hidden" value="">
              <input name="locality" type="hidden" value="">

              <button class="back new-post-toggle" type="button">Til baka</button>
              <button class="submit" type="submit">Staðfesta</button>
            </fieldset>
          </form>
        </div>
      </div>
      <div class="posts">
        <c:if test = "${posts != null}">
          <div class="posts">
            <c:forEach items="${posts}" var="post">
              <c:if test = "${!post.isArchived()}">
                <div class="post">
                  <h2><c:out value = "${post.getTitle()}"/></h2>
                  <h3><c:out value = "Notandi: ${post.getAccount().getUser()}"/></h3>
                  <p><c:out value = "${post.getDating()}"/></p>
                  <p><c:out value = "Lýsing: ${post.getDescription()}"/></p>
                  <p><c:out value = "Stuðningur: ${post.getSupport()}"/></p>
                  <p><c:out value = "(${post.getLatitude()}, ${post.getLongitude()})"/></p>
                  <p><c:out value = "${post.getRoad().getName()}"/></p>
                  <figure>
                    <img src="img/posts/${post.getPhotoURL()}" alt="Meðfylgjandi mynd">                         
                  </figure>
                </div>                  
              </c:if>
            </c:forEach>
          </div>
        </c:if>
      </div>
    </main>
    <script src="<c:url value="js/posts.js" />"></script>
    <script src="<c:url value="js/autocomplete.js" />"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGz-droHcXcK93KzuCjzjrR7xr2QnQrjA&libraries=places&callback=initAutocompletePlaceSearch" async defer></script>
  </body>
</html>
