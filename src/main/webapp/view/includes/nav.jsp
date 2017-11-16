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
      
      <a href="/">
       <i class="fa fa-home" aria-hidden="true"></i> 
       Heim
      </a>
    </div>
    <c:choose>
      <c:when test="${user == null}">
        <div class="s-account">
          
          <a href="/innskraning">
            <i class="fa fa-sign-in" aria-hidden="true"></i>
            Innskrá
          </a>
        </div>
      </c:when>    
      <c:otherwise>
        <div class="s-account">
          <a href="/innlegg">
            <i class="fa fa-plus" aria-hidden="true"></i>
            Nýtt innlegg
          </a>
        </div>
        <div class="s-account">
          <a href="/minar-sidur">
            <i class="fa fa-user" aria-hidden="true"></i>
            Mínar síður
          </a>
        </div>
      </c:otherwise>
    </c:choose>
    <div class="s-account">
      <c:choose>
        <c:when test="${user == null}">
          <a href="/nyskraning">
            <i class="fa fa-user-plus" aria-hidden="true"></i>
            Nýskrá
          </a>
        </c:when>    
        <c:otherwise>
          <a href="/utskra">
            <i class="fa fa-sign-out" aria-hidden="true"></i>
            Útskrá
          </a>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</nav>