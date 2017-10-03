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
  </head>
  <body>
    <jsp:include page="includes/header.jsp" />
    
    <main>
      <jsp:include page="includes/nav.jsp" />
      
      <section class="section-1">
        <div class="btn-container">
          <div class="btn">
            <span>Ábendingar</span>
            <i class="fa fa-lightbulb-o" aria-hidden="true"></i>
          </div>
          <div class="btn">
            <span>Lokið</span>
            <i class="fa fa-check" aria-hidden="true"></i>
          </div>
          <div class="btn">
            <span>Í ferli</span>
            <i class="fa fa-spinner" aria-hidden="true"></i>
          </div>
          <div class="btn">
            <span>Hafnað</span>
            <i class="fa fa-ban" aria-hidden="true"></i>
          </div>
        </div>

        <jsp:include page="includes/posts_container.jsp" />

      </section>

      <section class="section-2">
        <div class="map-wrapper">
          <div id="map"></div>
        </div>
      </section>
    </main>
        
    <jsp:include page="includes/footer.jsp" />

    <script src="js/jquery-3.2.0.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGz-droHcXcK93KzuCjzjrR7xr2QnQrjA" async defer></script>
    <script src="<c:url value="js/map.js" />"></script>
    <script src="<c:url value="js/main.js" />"></script>
  </body>
</html>