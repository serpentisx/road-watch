<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
      <h1>Vegavaktin</h1>
    </header>
    <main>
      <div class='create-new-entry'>
        <input id="new-entry" type="checkbox" name="create-new-entry" class="new-entry-checkbox">
        <label for="new-entry"> Nýtt innlegg </label>
        <div class="new-entry-container">
          <form action="/new-entry" method="POST">
            <fieldset>
              <legend>Nýtt innlegg</legend>
              <div>
                <label for="title">Titill:</label>
                <input type="text" name="title" id="title" maxlength="20" required>
              </div>
              <div>
                <label for="description">Lýsing:</label>
                <textarea aria-describedby="descriptiontext" name="description" id="description" cols="30" rows="5"></textarea>
                <span id="descriptiontext" aria-hidden="true">Skrifaðu stutta lýsingu</span>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
      <div class="entries">
        <!-- Insert new entries here -->
      </div>
    </main>
    <footer></footer>
  </body>
</html>
