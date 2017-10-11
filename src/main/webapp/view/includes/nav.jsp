<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %> 

  <nav>
    <div class="logo">
      <img src="/img/logo.png" alt="Logo">
    </div>
    <c:if test = "${username == null}">
      <a href="/innskraning">Innskrá</a>
    </c:if>
    <c:if test = "${username != null}">
      <label class="mobile-label m-pos" for="mobile-check"></label>
      <div class="mobile-nav-icon m-pos">
        <div class="line line-1"></div>
        <div class="line line-2"></div>
        <div class="line line-3"></div>
      </div>
    </c:if>
  </nav>
  <c:if test = "${username != null}">
    <input id="mobile-check" type="checkbox">
    <div class="mobile-lg"></div>
    <div class="mobile-nav">
      <div class="mobile-nav-container">
        <div class="user-panel">
          <span>${username}</span>
          <div class="btn">
            <form method="POST" action="/innlegg">
              <label for="new-post">Skrá nýtt innlegg</label>
              <button id="new-post" type="submit" style="display: none;">Skrá nýja færslu</button>
            </form>
          </div>
        </div>
        <div class="user-nav">
          <div class="un un-1">
            <div class>
              <i class="fa fa-user-o" aria-hidden="true"></i>
              Mínar síður
            </div>
            <a href="#">Mín innlegg</a>
            <a href="#">Líkað við</a>
          </div>
          <div class="un un-2">
            <div>
              <i class="fa fa-cog" aria-hidden="true"></i>
              Stillingar
            </div>
            <a href="/reikningur/breyta-nafni">Breyta notandanafni</a>
            <a href="/reikningur/breyta-lykilordi">Breyta lykilorði</a>
            <a href="/reikningur/eyda-reikningi">Eyða reikningi</a>
          </div>
          <div class="btn">
            <a href="/utskra">Skrá út</a>
          </div>
        </div>
      </div>
    </div>
  </c:if>