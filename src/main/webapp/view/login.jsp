<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="is">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Vegavaktin</title>
    <link href="<c:url value="/css/font-awesome-4.7.0/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/login.css" />" rel="stylesheet">
    <script src='https://www.google.com/recaptcha/api.js' async defer></script>
  </head>
  <body>
    <main>
      <div class="layer"></div>
      <div class="bg">
        <div class="container">
          <div class="m-title">
            <h1>Vegavaktin</h1>
            <div class="line"></div>
          </div>
          <div class="form">
            <c:choose>
                <c:when test="${formType == 'login'}">
                  <form class="register-form"  method="POST" action="/nyskraning" style="display: none;">
                </c:when>    
                <c:otherwise>
                  <form class="register-form"  method="POST" action="/nyskraning" style="display: block;">
                </c:otherwise>
            </c:choose>
              <div>
                <label for="register_user"></label>
                <input autofocus id="register_user" name="register_name" type="text" required placeholder="Nafn"/>
              </div>
              <div>
                <label for="register_email"></label>
                <input id="register_email" name="register_email" type="text" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="Netfang"/>
              </div>
              <div>
                <label for="register_password"></label>
                <input id="register_password" name="register_password" type="password" required placeholder="Lykilorð" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Verður að innihalda að lágmarki 6 stafi, a.m.k. einn tölustaf, einn hástaf og einn lágstaf."/>
              </div>
              <div class="g-recaptcha" data-sitekey="6LcMPjQUAAAAAFdXpqjP3JUSZj7V3ffp7IfKvG-r"></div>
              <div>
                <label for="register_submit"></label>
                <input id="register_submit" class="submit-btn" type="submit" name="create" value="Nýskrá">
              </div>
              <p class="message login-form">Þegar með aðgang? <a class="login-form" href="#">Innskrá</a></p>
            </form>
            <c:choose>
                <c:when test="${formType == 'login'}">
                  <form class="login-form" method="POST" action="/innskraning" style="display: block;">
                </c:when>    
                <c:otherwise>
                  <form class="login-form" method="POST" action="/innskraning" style="display: none;">
                </c:otherwise>
            </c:choose>
              <div>
                <label for="login_email"></label>
                <input autofocus name="login_email" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required placeholder="Netfang">
              </div>
              <div>
                <label for="login_password"></label>
                <input id="login_password" name="login_password" type="password" required placeholder="Lykilorð"/>
              </div>
              <div>
                <label for="login_submit"></label>
                <input id="login_submit" name="login_submit" class="submit-btn" type="submit" name="login" value="Innskrá">
              </div>
              <p class="message">Ekki með aðgang? <a class="register-form" href="#">Skráðu þig hér</a></p>
              <p class="message">Gleymt lykilorð? <a class="fpw-form" href="#">Endurstilla lykilorð</a></p>
            </form>
            <form class="fpw-form" method="POST" action="/gleymt-lykilord" style="display: none;">
              <div>
                <input id="submit_email" name="submit_email" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required placeholder="Þitt netfang">
              </div>
              <div>
                <input id="submit-email-btn" name="submit-email-btn" class="submit-btn" type="submit" value="Áfram">
              </div>
              <p class="message">Þegar með aðgang? <a class="login-form" href="#">Innskrá</a></p>
            </form>
            <span class="invalid-input">${error_message}</span>
            <span class="success-message">${success_message}</span>
          </div>
        </div>
      </div>
    </main>
    <script src="<c:url value="/js/jquery-3.2.0.min.js" />"></script>
    <script src="<c:url value="/js/login.js" />"></script>
  </body>
</html>
