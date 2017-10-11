<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="is">
  <head>
    <meta charset="utf-8">
    <title>Vegavaktin</title>
    <link href="<c:url value="/css/font-awesome-4.7.0/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/account.css" />" rel="stylesheet">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
  </head>
  <body>
    <main>
      <jsp:include page="includes/nav.jsp" />
      <jsp:include page="includes/header.jsp" />
      <c:if test = "${form_switch == 'delete'}">
        <h2>Eyða aðgangi</h2>
        <p>Skrifaðu lykilorðið þitt tvisvar til þess að eyða aðganginum</p>
        <form method="POST" action="/reikningur/eyda-reikningi">
          <div>
            <label for="password"></label>
            <input id="password" name="password" type="password" required placeholder="Lykilorð">
          </div>
          <button type="submit">Staðfesta</button>
        </form>
      </c:if>

      <c:if test = "${form_switch == 'password'}">
        <h2>Breyta lykilorði</h2>
        <form method="POST" action="/reikningur/breyta-lykilordi">
          <div>
            <label for="old-password"></label>
            <input id="old-password" name="old_password" type="password" required placeholder="Núverandi lykilorð">
          </div>
          <div>
            <label for="new-password1"></label>
            <input id="new-password1" name="new_password_1" type="password" required placeholder="Nýtt lykilorð" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Verður að innihalda að lágmarki 6 stafi, a.m.k. einn tölustaf, einn hástaf og einn lágstaf.">
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
        <form method="POST" action="/reikningur/breyta-nafni">
          <div>
            <label for="username"></label>
            <input id="username" name="username" type="text" required placeholder="Nýtt notandanafn">
          </div>
          <button type="submit">Staðfesta</button>
        </form>
      </c:if>

      <span class="message">${message}</span>
      <jsp:include page="includes/footer.jsp" />
    </main>
    <jsp:include page="includes/scripts.jsp" />
  </body>
</html>
