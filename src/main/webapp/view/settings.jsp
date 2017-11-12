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
                    <img alt='' class='sidebar__avatar' src='/img/profile.png'>
                    <p>${username}</p>
                </div>
                <div class='sidebar__menu-item sidebar__menu-item--active account-info'>
                    <i class="fa fa-user" aria-hidden="true"></i> Aðgangur
                </div>
                <div class='sidebar__menu-item my-posts'>
                    <i class="fa fa-sticky-note" aria-hidden="true"></i> Innleggin mín
                </div>
                <div class='sidebar__menu-item'>
                    <i class="fa fa-thumbs-up" aria-hidden="true"></i> Líkað við
                </div>
            </div>
            <div class='main'>
                <div class="account-info">
                    <div class='main__header'>
                        <div class="account-settings-header">
                           <div class="as-label username-settings">Breyta notandanafni</div>
                           <div class="as-label password-settings">Breyta lykilorði</div>
                           <div class="as-label delete-account">Eyða reikningi</div>
                        </div>
                    </div>
                    <div class='main__content'>
                        <div class='main__settings-form username-settings'>
                            <form action='/reikningur/breyta-nafni' method='post'>
                                <label class='main__input-label'>Þitt notandanafn</label>
                                <input name="username" class='main__input' type='text' value="${username}">
                                <button class='btn main__save-button' type="submit">Vista</button>
                            </form>
                        </div>
                        <div class='main__settings-form password-settings'>
                            <form action='/reikningur/breyta-lykilordi' method='post'>
                                <label class='main__input-label'>Gamalt lykilorð</label>
                                <input name="old-password" class='main__input' type='password'>
                                <label class='main__input-label'>Nýtt lykilorð</label>
                                <input name="new-password1" class='main__input' type='password'>
                                <label class='main__input-label'>Nýtt lykilorð - endurtekið</label>
                                <input name="new-password2" class='main__input' type='password'>
                                <button class='btn main__save-button' type="submit">Staðfesta</button>
                            </form>
                        </div>
                        <div class='main__settings-form delete-account'>
                            <form action='/reikningur/eyda-reikningi' method='post'>
                                <h3>Þú ert að fara að eyða reikningi. Þessi aðgerð er óafturkallanleg.</h3>
                                <label class='main__input-label'>Lykilorð þitt</label>
                                <input name="password" class='main__input' type='password'>
                                <button class='btn main__save-button danger-btn' type="submit">Eyða reikningi</button>                      
                            </form>
                        </div>    
                    </div>
                </div>
                
                <div class="my-posts">
                
                </div>
                
                <div class="supported-posts">

                </div>
                
                <p class="message">${message}</p>        
            </div>
        </div>
    </main>
    <jsp:include page="includes/footer.jsp" />
    
    <script src="<c:url value="/js/jquery-3.2.0.min.js" />"></script>
    <script src="<c:url value="/js/settings.js" />"></script>
    
  </body>
  
</html>
