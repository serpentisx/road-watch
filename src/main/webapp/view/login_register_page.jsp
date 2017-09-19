<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="is">
    <head>
        <meta charset="utf-8">
        <title>Vegavaktin</title>
        <link href="<c:url value="css/font-awesome-4.7.0/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="css/style.css" />" rel="stylesheet">
        <link href="<c:url value="css/login_register.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="layer"></div>
        <div class="bg">
            <div class="container">
                <div class="m-title">
                    <h1>Vegavaktin</h1>
                    <div class="line"></div>
                </div>
                <div class="form">
                    <form class="register-form"  method="POST" action="/register">
                        <input name="register_name" type="text" placeholder="nafn"/>
                        <input name="register_password" type="password" placeholder="lykilorð"/>
                        <input name="register_email" type="text" placeholder="netfang"/>
                        <input class="submit-btn" type="submit" name="create" value="Nýskrá">
                        <p class="message">Þegar með aðgang? <a href="#">Innskrá</a></p>
                    </form>
                    <form class="login-form" method="POST" action="/posts">
                        <input name="login_name" type="text" placeholder="nafn">
                        <input name="login_password" type="password" placeholder="lykilorð"/>
                        <input class="submit-btn" type="submit" name="login" value="Innskrá">
                        <p class="message">Ekki með aðgang? <a href="#">Skráðu þig hér</a></p>
                    </form>
                    <span class="invalid-input">${invalid_input}</span>
                </div>
            </div>
        </div>
        <script src="<c:url value="js/jquery-3.2.0.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="js/main.js" />" type="text/javascript"></script>
    </body>
</html>
