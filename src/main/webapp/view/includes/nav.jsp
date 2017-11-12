<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %> 

<nav class="main-navigation header-bar">
  <div class="logo">
    <a href="/"></a>
    <img class="logo-img" src="../img/logo.png" alt="">
  </div>
  <a href="/">Heim</a>
  <div class="account-wrapper">
    <div class="s-account sign-in">
      <c:choose>
        <c:when test="${user == null}">
            <i class="fa fa-sign-in" aria-hidden="true"></i>
            <a href="/innskraning">Innskrá</a>
        </c:when>    
        <c:otherwise>
            <i class="fa fa-user" aria-hidden="true"></i>
            <a href="/minar-sidur">Mínar síður</a>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="s-account sign-out">
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