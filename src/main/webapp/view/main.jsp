<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
   <head>
      <meta charset="utf-8">
      <title>Vegavaktin</title>
      <link rel="stylesheet" type="text/css" href="css/font-awesome-4.7.0/css/font-awesome.min.css">
      <link href="<c:url value="css/main.css" />" rel="stylesheet">
   </head>
   <body>
      <main>
         <nav>
            <div class="logo">
               <img src="img/logo.png" alt="Logo">
            </div>
            <a href="/login">Innskrá</a>
            <label class="mobile-label m-pos" for="mobile-check"></label>
            <div class="mobile-nav-icon m-pos">
              <div class="line line-1"></div>
              <div class="line line-2"></div>
              <div class="line line-3"></div>
            </div>
         </nav>
         <input id="mobile-check" type="checkbox">
         <div class="mobile-lg"></div>
         <div class="mobile-nav">
            <div class="mobile-nav-container">
               <div class="user-panel">
                  <span>Jón Jónsson</span>
                  <div class="btn"><a href="posts">Skrá nýja færslu</a></div>
               </div>
               <div class="user-nav">
                  <div class="un un-1">
                     <div class>
                        <i class="fa fa-user-o" aria-hidden="true"></i>
                        Minar síður
                     </div>
                     <a href="#">Færslur</a>
                     <a href="#">Líkað við</a>
                  </div>
                  <div class="un un-2">
                     <div>
                        <i class="fa fa-cog" aria-hidden="true"></i>
                        Stillingar
                     </div>
                     <a href="#">Breyta notandanafn</a>
                     <a href="#">Breyta lykilorð</a>
                  </div>
                  <div class="btn">
                     Skrá út
                  </div>
               </div>
            </div>
         </div>
         <header>
            <div class="lg"></div>
            <div class="layer"></div>
            <div class="header-bg">
               <div class="header-container">
                  <div class="m-title">
                     <h1>Vegavaktin</h1>
                     <div class="line"></div>
                  </div>
                  <div class="undertitle">
                     Lorem ipsum dolar
                  </div>
               </div>
            </div>
         </header>
         <section class="section-1">
            <div class="btn-container">
               <div class="btn">
                  <span>Ábendingar</span>
                  <i class="fa fa-lightbulb-o" aria-hidden="true"></i>
               </div>
               <div class="btn">
                  <span>Lokið</span>
                  <i class="fa fa-check" aria-hidden="true"></i>
               </div>
               <div class="btn">
                  <span>Í ferli</span>
                  <i class="fa fa-spinner" aria-hidden="true"></i>
               </div>
               <div class="btn">
                  <span>Hafnað</span>
                  <i class="fa fa-ban" aria-hidden="true"></i>
               </div>
            </div>
            <div class="posts-container">
               <div class="post-item-wrapper">
                  <div class="post-item">
                     <div class="post-img">
                        <div class="heart-img">
                           <i class="fa fa-heart" aria-hidden="true"></i>
                        </div>
                     </div>
                     <div class="post-content-wrapper">
                        <span class="post-category">Vegir</span>
                        <h3 class="post-title">Lorem ipmsum Dolar Sit Amet</h3>
                        <p class="post-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiamSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam</p>
                        <div class="post-author">
                           <div class="pa-img"></div>
                           <span class="pa-name">Jón Jónsson</span>
                        </div>
                        <div class="line"></div>
                        <div class="reaction-container">
                           <div class="rc-img likes">
                              <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              <span>24</span>
                           </div>
                           <div class="rc-img dislikes">
                              <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                              <span>3</span>
                           </div>
                           <div class="rc-img comments">
                              <i class="fa fa-comment" aria-hidden="true"></i>
                              <span>5</span>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="post-item-wrapper">
                  <div class="post-item">
                     <div class="post-img">
                        <div class="heart-img">
                           <i class="fa fa-heart" aria-hidden="true"></i>
                        </div>
                     </div>
                     <div class="post-content-wrapper">
                        <span class="post-category">Vegir</span>
                        <h3 class="post-title">Lorem ipmsum Dolar Sit Amet</h3>
                        <p class="post-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiamSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam</p>
                        <div class="post-author">
                           <div class="pa-img"></div>
                           <span class="pa-name">Jón Jónsson</span>
                        </div>
                        <div class="line"></div>
                        <div class="reaction-container">
                           <div class="rc-img likes">
                              <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              <span>24</span>
                           </div>
                           <div class="rc-img dislikes">
                              <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                              <span>3</span>
                           </div>
                           <div class="rc-img comments">
                              <i class="fa fa-comment" aria-hidden="true"></i>
                              <span>5</span>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="post-item-wrapper">
                  <div class="post-item">
                     <div class="post-img">
                        <div class="heart-img">
                           <i class="fa fa-heart" aria-hidden="true"></i>
                        </div>
                     </div>
                     <div class="post-content-wrapper">
                        <span class="post-category">Vegir</span>
                        <h3 class="post-title">Lorem ipmsum Dolar Sit Amet</h3>
                        <p class="post-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiamSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam</p>
                        <div class="post-author">
                           <div class="pa-img"></div>
                           <span class="pa-name">Jón Jónsson</span>
                        </div>
                        <div class="line"></div>
                        <div class="reaction-container">
                           <div class="rc-img likes">
                              <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              <span>24</span>
                           </div>
                           <div class="rc-img dislikes">
                              <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                              <span>3</span>
                           </div>
                           <div class="rc-img comments">
                              <i class="fa fa-comment" aria-hidden="true"></i>
                              <span>5</span>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="post-item-wrapper">
                  <div class="post-item">
                     <div class="post-img">
                        <div class="heart-img">
                           <i class="fa fa-heart" aria-hidden="true"></i>
                        </div>
                     </div>
                     <div class="post-content-wrapper">
                        <span class="post-category">Vegir</span>
                        <h3 class="post-title">Lorem ipmsum Dolar Sit Amet</h3>
                        <p class="post-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiamSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam</p>
                        <div class="post-author">
                           <div class="pa-img"></div>
                           <span class="pa-name">Jón Jónsson</span>
                        </div>
                        <div class="line"></div>
                        <div class="reaction-container">
                           <div class="rc-img likes">
                              <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              <span>24</span>
                           </div>
                           <div class="rc-img dislikes">
                              <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                              <span>3</span>
                           </div>
                           <div class="rc-img comments">
                              <i class="fa fa-comment" aria-hidden="true"></i>
                              <span>5</span>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="post-item-wrapper">
                  <div class="post-item">
                     <div class="post-img">
                        <div class="heart-img">
                           <i class="fa fa-heart" aria-hidden="true"></i>
                        </div>
                     </div>
                     <div class="post-content-wrapper">
                        <span class="post-category">Vegir</span>
                        <h3 class="post-title">Lorem ipmsum Dolar Sit Amet</h3>
                        <p class="post-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiamSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam</p>
                        <div class="post-author">
                           <div class="pa-img"></div>
                           <span class="pa-name">Jón Jónsson</span>
                        </div>
                        <div class="line"></div>
                        <div class="reaction-container">
                           <div class="rc-img likes">
                              <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              <span>24</span>
                           </div>
                           <div class="rc-img dislikes">
                              <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                              <span>3</span>
                           </div>
                           <div class="rc-img comments">
                              <i class="fa fa-comment" aria-hidden="true"></i>
                              <span>5</span>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="post-item-wrapper">
                  <div class="post-item">
                     <div class="post-img">
                        <div class="heart-img">
                           <i class="fa fa-heart" aria-hidden="true"></i>
                        </div>
                     </div>
                     <div class="post-content-wrapper">
                        <span class="post-category">Vegir</span>
                        <h3 class="post-title">Lorem ipmsum Dolar Sit Amet</h3>
                        <p class="post-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiamSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam</p>
                        <div class="post-author">
                           <div class="pa-img"></div>
                           <span class="pa-name">Jón Jónsson</span>
                        </div>
                        <div class="line"></div>
                        <div class="reaction-container">
                           <div class="rc-img likes">
                              <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              <span>24</span>
                           </div>
                           <div class="rc-img dislikes">
                              <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                              <span>3</span>
                           </div>
                           <div class="rc-img comments">
                              <i class="fa fa-comment" aria-hidden="true"></i>
                              <span>5</span>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </section>
         <section class="section-2">
            <div class="map-wrapper">
               <div id="map"></div>
            </div>
         </section>
         <footer class="section-3">
            <div class="footer-wrapper">
               <div class="logo">
                  <img src="img/logo.png" alt="Logo">
               </div>
               <span>Vegavaktin 2017</span>
            </div>
         </footer>
      </main>
      <script src="js/jquery-3.2.0.min.js"></script>
      <script src="<c:url value="https://maps.googleapis.com/maps/api/js?key=AIzaSyCeFyymUYS6SvJz6AMFdZcspDvPrhA33C4" />" type="text/javascript"></script>
      <script src="<c:url value="js/map.js" />" type="text/javascript"></script>
      <script src="<c:url value="js/main.js" />" type="text/javascript"></script>
   </body>
</html>
