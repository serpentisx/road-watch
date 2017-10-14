<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %> 

<nav class="main-navigation header-bar">
  <div class="logo">
    <img class="logo-img" src="../img/logo.png" alt="">
  </div>
  <div class="account-wrapper">
    <div class="s-account sign-in">
      <i class="fa fa-sign-in" aria-hidden="true"></i>
      <a href="#">Innskrá</a>
    </div>
    <div class="s-account sign-out">
      <i class="fa fa-user-plus" aria-hidden="true"></i>
      <a href="#">Nýskrá</a>
    </div>
  </div>
</nav>