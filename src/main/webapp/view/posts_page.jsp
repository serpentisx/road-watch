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
    <link href="<c:url value="css/posts.css" />" rel="stylesheet">
  </head>
  <body>
    <header>
      <nav>
        <ul>
          <li><a href='/'>Skrá út</a></li>
          <li><a href='./posts'>Innlegg</a></li>
        </ul>
      </nav>
      <h1><a href='./'>Vegavaktin</a></h1>
    </header>
    <main>
      <div class='create-new-entry'>
        <!--<input id="new-entry" type="checkbox" name="create-new-entry" class="new-entry-checkbox" value="switch">
        <label for="new-entry"> Nýtt innlegg </label>-->
        <button class="fixed new-entry-toggle" type="button">Nýtt innlegg</button>
        <div class="new-entry-container">
          <form action="/new-entry" method="POST">
            <fieldset>
              <legend>Nýtt innlegg</legend>
              <div>
                <label for="title"></label>
                <input autofocus type="text" name="title" id="title" maxlength="20" required placeholder="Titill">
              </div>
              <div class="description">
                <div id="descriptiontext" aria-hidden="true">Skrifaðu stutta lýsingu á annmarkanum</div>
                <label for="description"></label>
                <textarea aria-describedby="descriptiontext" name="description" id="description" cols="42" rows="6" maxlength="150" required></textarea>
              </div>
              <button class="back new-entry-toggle" type="button">Til baka</button>
              <button class="submit" type="submit">Staðfesta</button>
            </fieldset>
          </form>
        </div>
      </div>
      <div class="posts">
        <c:if test = "${post != null}">
          <div class="post">
            <h2><c:out value = "${post.getTitle()}"/></h2>
            <p><c:out value = "${post.getDescription()}"/></p>
          </div>
        </c:if>
      </div>
    </main>
    <script src="<c:url value="js/posts.js" />" type="text/javascript"></script>
  </body>
</html>
