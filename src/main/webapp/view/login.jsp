<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="is">
    <head>
        <meta charset="utf-8">
        <title>Vegavaktin</title>
        <link href="<c:url value="css/font-awesome-4.7.0/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="css/login.css" />" rel="stylesheet">
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
                        <div>
                            <!--<i class="fa fa-user-o" aria-hidden="true"></i>-->
                            <label for="register_user"></label>
                            <input id="register_user" name="register_name" type="text" placeholder="notandanafn" pattern="[a-zA-z]+[a-zA-Z0-9]*$" title="Notandanafnið verður að byrja á bókstaf." />
                        </div>
                        <div>
                            <!--<i class="fa fa-at" aria-hidden="true"></i>-->
                            <label for="register_email"></label>
                            <input id="register_email" name="register_email" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="netfang"/>
                        </div>
                        <div>
                            <!--<i class="fa fa-lock" aria-hidden="true"></i>-->
                            <label for="register_password"></label>
                            <input id="register_password" name="register_password" type="password" placeholder="lykilorð" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Verður að vera a.m.k. 6 stafa langt og innihalda a.m.k. einn tölustaf, einn hástaf og lágstaf."/>
                        </div>
                        <div>
                            <label for="register_submit"></label>
                            <input id="register_submit" class="submit-btn" type="submit" name="create" value="Nýskrá">
                        </div>
                        <p class="message">Þegar með aðgang? <a href="#">Innskrá</a></p>
                    </form>
                    <form class="login-form" method="POST" action="/login">
                        <div>
                            <!--<i class="fa fa-at" aria-hidden="true"></i>-->
                            <label for="login_email"></label>
                            <input name="login_email" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="netfang">
                        </div>
                        <div>
                            <!--<i class="fa fa-key" aria-hidden="true"></i>-->
                            <label for="login_password"></label>
                            <input id="login_password" name="login_password" type="password" placeholder="lykilorð"/>
                        </div>
                        <div>
                            <label for="login_submit"></label>
                            <input id="login_submit" name="login_submit" class="submit-btn" type="submit" name="login" value="Innskrá">
                        </div>
                        <p class="message">Ekki með aðgang? <a href="#">Skráðu þig hér</a></p>
                    </form>
                    <span class="invalid-input">${invalid_input}</span>
                    <span class="success-message">${success_message}</span>
                </div>
            </div>
        </div>
        <script src="<c:url value="js/jquery-3.2.0.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="js/main.js" />" type="text/javascript"></script>
    </body>
</html>
