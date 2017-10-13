<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="is">
  <head>
    <meta charset="utf-8">
    <title>Vegavaktin</title>
    <link rel="stylesheet" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="<c:url value="/css/index.css" />" rel="stylesheet">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
  </head>
  <body>
    
    <main>
      <jsp:include page="includes/nav.jsp" />
      <jsp:include page="includes/header.jsp" />
      
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
      
      <jsp:include page="includes/footer.jsp" />
    </main>
    <jsp:include page="includes/scripts.jsp" />
    
    <script type="text/javascript">
      var posts = ${postsJSON};
    </script>
    
    <script src="<c:url value="https://maps.googleapis.com/maps/api/js?key=AIzaSyCeFyymUYS6SvJz6AMFdZcspDvPrhA33C4" />"></script>
    <script src="<c:url value="/js/map.js" />"></script>
  </body>
</html>