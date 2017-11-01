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
    <link href="<c:url value="/css/navigation.css" />" rel="stylesheet">
    <link href="<c:url value="/css/footer.css" />" rel="stylesheet">
    <link href="<c:url value="/css/map.css" />" rel="stylesheet">
  </head>
  
  <body>
    
    <header id="s0">
      <div class="header-container">
        <div class="lg"></div>
        <div class="layer"></div>
        <div class="header-bg">
          <jsp:include page="includes/nav.jsp" />
          <div class="h-wrapper">
            <div class="m-title">
              <h1>Vegavaktin</h1>
            </div>
            <div class="undertitle">
              Með augun á veginum
            </div>
            <div class="btn header-btn">
              <a href="/innlegg">Nýtt innlegg</a>
            </div>
          </div>
        </div>
      </div>
      <nav class="index-navigation">
        <ul>
          <li><a href="#s0">Heim</a></li>
          <li><a href="#s1">Innlegg</a></li>
          <li><a class="map-label" href="#">Kort</a></li>
          <li><a href="#s2">Hafa samband</a></li>
        </ul>
      </nav>
    </header>
          
    <main>
      <section id="s1" class="section-1">
        <div class="section-head">
          <h2>Innlegg</h2>
          <div class="line"></div>
        </div>
        <jsp:include page="includes/posts_container.jsp" />
        <c:if test = "${posts.size() > 6}">
          <div class="btn p-see-more-btn">Sjá meira</div>
        </c:if>
      </section>
    </main>
    
    <div class="section-2 footer">
      <div class="section-contact">
        <div class="layer"></div>
        <div class="contact-container">
          <h2>hafa samband</h2>
          <form class="contact-form" action="/senda-post" method="post">
            <div class="f-wrapper">
              <input type="text" name="contact-email" placeholder="Netfang">
            </div>
            <div class="mf-wrapper">
              <input type="text" name="contact-name" placeholder="Nafn">
              <textarea name="contact-message" rows="8" cols="80" placeholder="Skilaboð"></textarea>
            </div>
            <input class="btn btn-form" type="submit" value="Senda">
          </form>
        </div>
      </div>
      <jsp:include page="includes/footer.jsp" />
      <div id="s2"></div>
    </div>
    <jsp:include page="includes/map.jsp" />
        
    <jsp:include page="includes/scripts.jsp" />
    
    <script type="text/javascript">
      var posts = ${postsJSON};
    </script>
    
    <script src="<c:url value="/js/map.js" />"></script>
    <script src="<c:url value="/js/index.js" />"></script>
    <script src="<c:url value="https://maps.googleapis.com/maps/api/js?key=AIzaSyCeFyymUYS6SvJz6AMFdZcspDvPrhA33C4&callback=initMap" />"></script>
  </body>
</html>