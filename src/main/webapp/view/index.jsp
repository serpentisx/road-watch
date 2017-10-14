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
  </head>
  
  <body>
    
    <header>
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
              <a href="/innlegg">Skrá annmark</a>
            </div>
          </div>
        </div>
      </div>
      <nav class="index-navigation">
        <ul>
          <li><a href="#">Heim</a></li>
          <li><a href="#">Annmarkir</a></li>
          <li><a href="#">Kort</a></li>
          <li><a href="#">Hafa samband</a></li>
        </ul>
      </nav>
    </header>
          
    <main>
      <section class="section-1">
        <div class="section-head">
          <h2>Annmarkir</h2>
          <div class="line"></div>
        </div>
        <jsp:include page="includes/posts_container.jsp" />
      </section>
    </main>
    
    <!-- Do not include footer here -->
    <footer class="section-3">
      <div class="section-2-contact">
        <div class="layer"></div>
        <div class="contact-container">
          <h2>Hafa samband</h2>
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
      <div class="footer-wrapper">
        <div class="logo">
          <a href="/"></a>
          <img src="img/logo.png" alt="Logo">
        </div>
        <span>Vegavaktin 2017</span>
      </div>
    </footer>
        
    <jsp:include page="includes/scripts.jsp" />
    
    <script type="text/javascript">
      var posts = ${postsJSON};
    </script>
    
    <script src="<c:url value="https://maps.googleapis.com/maps/api/js?key=AIzaSyCeFyymUYS6SvJz6AMFdZcspDvPrhA33C4" />"></script>
    <script src="<c:url value="/js/map.js" />"></script>
  </body>
</html>