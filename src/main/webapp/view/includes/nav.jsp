<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %> 

<nav class="main-navigation header-bar">
  <div class="logo">
    <a href="/"></a>
    <img class="logo-img" src="../img/logo.png" alt="">
  </div>
  <div class="account-wrapper">
    <div class="s-account">
      <i class="fa fa-home" aria-hidden="true"></i>
      <a href="/">Heim</a>
    </div>
    <c:choose>
      <c:when test="${user == null}">
        <div class="s-account">
          <i class="fa fa-sign-in" aria-hidden="true"></i>
          <a href="/innskraning">Innskrá</a>
        </div>
      </c:when>    
      <c:otherwise>
        <div class="s-account">
          <i class="fa fa-plus" aria-hidden="true"></i>
          <a href="/innlegg">Nýtt innlegg</a>
        </div>
        <div class="s-account">
          <i class="fa fa-user" aria-hidden="true"></i>
          <a href="/minar-sidur">Mínar síður</a>
        </div>
      </c:otherwise>
    </c:choose>
    <div class="s-account">
      <c:choose>
        <c:when test="${user == null}">
          <i class="fa fa-user-plus" aria-hidden="true"></i>
          <a href="/nyskraning">Nýskrá</a>
        </c:when>    
        <c:otherwise>
          <i class="fa fa-sign-out" aria-hidden="true"></i>
          <a href="/utskra">Útskrá</a>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</nav>