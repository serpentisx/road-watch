<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %> 

<html lang="is">
  <head>
    <meta charset="utf-8">
    <title>Vegavaktin</title>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="<c:url value="/css/settings.css" />" rel="stylesheet">
    <link href="<c:url value="/css/navigation.css" />" rel="stylesheet">
    <link href="<c:url value="/css/footer.css" />" rel="stylesheet">
  </head>
  
  <body>
    <header>
        <jsp:include page="includes/nav.jsp" />
        <h1>Mínar síður</h1>
    </header>
        
    <main>
        <div class='container'>
            <div class='sidebar'>
                <div class='sidebar__header'>
                    <img alt='' class='sidebar__avatar' src='img/profile.png'>
                    <p>${username}</p>
                </div>
                <div class='sidebar__menu-item sidebar__menu-item--active'>
                    <i class="fa fa-user" aria-hidden="true"></i> Aðgangur
                </div>
                <div class='sidebar__menu-item'>
                    <i class="fa fa-sticky-note" aria-hidden="true"></i> Mínar innleggir
                </div>
                <div class='sidebar__menu-item'>
                    <i class="fa fa-thumbs-up" aria-hidden="true"></i> Líkað við
                </div>
            </div>
            <div class='main'>
                <div class='main__header'>
                    <div class="account-settings-header">
                       <div class="as-label username-settings">Breyta notandanafn</div>
                       <div class="as-label password-settings">Breyta lykilorð</div>
                    </div>
                </div>
                <div class='main__content'>
                    <div class='main__settings-form username-settings'>
                        <form action='#' method='post'>
                            <label class='main__input-label'>Þitt notandanafn</label>
                            <input name="new-username" class='main__input' type='text' value=${username}>
                        </form>
                        <button class='btn main__save-button' type="submit">Vista</button>
                    </div>
                    <div class='main__settings-form password-settings'>
                        <form action='#' method='post'>
                            <label class='main__input-label'>Gamalt lykilorð</label>
                            <input name="old-password" class='main__input' type='password'>
                            <label class='main__input-label'>Nýtt lykilorð</label>
                            <input name="new-password1" class='main__input' type='password'>
                            <label class='main__input-label'>Nýtt lykilroð - endurtekið</label>
                            <input name="new-password2" class='main__input' type='password'>
                        </form>
                        <button class='btn main__save-button' type="submit">Staðfesta</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="includes/footer.jsp" />
    
    <jsp:include page="includes/scripts.jsp" />
    <script src="<c:url value="/js/settings.js" />"></script>
    
  </body>
  
</html>
