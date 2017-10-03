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
    <link href="<c:url value="css/account.css" />" rel="stylesheet">
  </head>
  <body>
    <jsp:include page="includes/nav.jsp" />
    <main>
      <c:if test = "${form_switch == 'delete'}">
        <h2>Eyða aðgangi</h2>
        <p>Skrifaðu lykilorðið þitt tvisvar til þess að eyða aðganginum</p>
        <form method="POST" action="/account/delete">
          <div>
            <label for="password1"></label>
            <input id="password1" name="passsword1" type="password" required placeholder="Lykilorð">
          </div>
          <div>
            <label for="password2"></label>
            <input id="password2" name="password2" type="password" required placeholder="Lykilorð endurtekið">
          </div>
          <button type="submit">Staðfesta</button>
        </form>
      </c:if>

      <c:if test = "${form_switch == 'password'}">
        <h2>Breyta lykilorði</h2>
        <form method="POST" action="/account/password">
          <div>
            <label for="old-password"></label>
            <input id="old-password" name="old_password" type="password" required placeholder="Núverandi lykilorð">
          </div>
          <div>
            <label for="new-password1"></label>
            <input id="new-password1" name="new_password_1" type="password" required placeholder="Nýtt lykilorð">
          </div>
          <div>
            <label for="new-password2"></label>
            <input id="new-password2" name="new_password_2" type="password" required placeholder="Nýtt lykilorð endurtekið">
          </div>
          <button type="submit">Staðfesta</button>
        </form>
      </c:if>

      <c:if test = "${form_switch == 'username'}">
        <h2>Breyta notandanafni</h2>
        <form method="POST" action="/account/username">
          <div>
            <label for="username"></label>
            <input id="username" name="username" type="text" required pattern="[a-zA-z]+[]*$" title="Notandanafnið verður að byrja á bókstaf." placeholder="Nýtt notandanafn">
          </div>
          <button type="submit">Staðfesta</button>
        </form>
      </c:if>

      <span class="message">${message}</span>
    </main>
  </body>
</html>
