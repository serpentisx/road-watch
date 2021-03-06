/* global posts, Promise */

// Javascript file for all actions on main page

(() => {
    // Root element
    var $root = $('html, body');
    
    // Initializing listeners
    initSupportPostListener();
    initSeeMoreButton();
    initMailListener();
    initMobileNavigation();
    
    // Map toggling
    window.addEventListener('resize', function() {
        if (window.innerWidth < 630) {
            $(window).unbind('scroll');
            $('.index-navigation a').css('display', 'none');
            $('.map-label').text('Skoða kort');
            $('.map-label').css('display', 'block');
        }
        else {
            initScrollWatching();
        }
    });

    // Auto scroll to anchor link
    $('.index-navigation a').click(function(event) {
        event.preventDefault();
        var id = $(this).attr("href");
        if (id === '#') return;
        var offset = 70;
        var target = $(id).offset().top - offset;
        var st = target;
        if (id === '#s2') {
            target = document.body.scrollHeight;
        }
        $('html, body').animate({
            scrollTop: target
        }, 500);
    });
    

    // Mobile navigation
    function initMobileNavigation() {
        if (window.innerWidth < 630) {
            $('.index-navigation a').css('display', 'none');
            $('.map-label').text('Skoða kort');
            $('.map-label').css('display', 'block');
        }
        else {
            initScrollWatching();
        }
    }

    // Event listener for see-more button
    function initSeeMoreButton() {
       // posts.forEach(w => console.log(w))
        posts.splice(0, 6);
        $('.p-see-more-btn').click(function () {
            var next = posts.splice(0, 6);
            for (var i = 0; i < next.length; i++) {
               $('.posts-container').append(generatePostElement(next[i]));
            }
            if (posts.length === 0) {
                $('.p-see-more-btn').remove();
            }
            initSupportPostListener();
        });
    }
    
    // Event listener for post support button
    function initSupportPostListener() {
        $('.rc-img').off();
        $('.rc-img').click(function () {
            var that = this;
            
            // Checks if a user is logged in or not.
            // Performs a post request to initialize a support if a user is logged in
            // or redirect to a log-in page if not.
            isLoggedIn().then(function (data) {
                if (!data) {
                    window.location.href = "/innskraning";
                    return;
                }
                var id = parseInt($(that).attr('id'));             
                toggleSupport(that);
                $.ajax({
                    type: "POST",
                    context: that,
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    url: "/supportPost",
                    data: JSON.stringify(id)
                });
            });
        });
    }
    
    // A mail listener for sending mails
    function initMailListener() {
        $("#contact-form").submit(function(e) {
            e.preventDefault();
            
            $("#e-send").prop('disabled', true);
            
            var mail = {
                'email'  : $('#e-email').val(),
                'name'   : $('#e-name').val(),
                'content': $('#e-content').val()
            };

            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: "/senda-post",
                data: JSON.stringify(mail),
                success: function (data) {
                    if (data) $("#e-send").attr('value', 'Takk! Við munum hafa samband');
                    else $("#e-send").attr('value', 'Úúúps, eitthvað fór úrskeiðis');
                    $("#e-send").css({
                      'cssText': 'background: #727f7e !important; width: 100% !important; transition: all 0.4s ease !important'
                    });
                },
                error: function(e) {
                    $("#e-send").attr('value', 'Úúúps, eitthvað fór úrskeiðis');
                    $("#e-send").css({
                      'cssText': 'background: #727f7e !important; width: 100% !important; transition: all 0.4s ease !important'
                    });
                }
            });
            return false;
        });
    }
    
    // Toggle between liked and un-liked icon on a post
    // @param post : the post the toggle like on
    function toggleSupport(post) {
        var support = parseInt($('span', post).text());
        if ($(post).hasClass('rc-img-active')) {
            $('span', post).text(support - 1);
        }
        else {
            $('span', post).text(support + 1);
        }
        $(post).toggleClass('rc-img-active');
    }
    
    // Checks if a user is logged in or not
    function isLoggedIn() {
        return Promise.resolve($.ajax({
            type: "POST",
            context: this,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: "/isLoggedIn"
        }));
    }

    // Creates a post element
    // @param post : the post create
    function generatePostElement(post) {
        if (post.archived) {
            return;
        }
        
        var date = post.date ? post.date : "";
        var supportClass = post.isSupporting ? "rc-img-active" : "rc-img likes";
        var authored = post.authored ? "pa-img-i-x " : "";
        var post =  `<div class="post-item-wrapper">
                        <div class="post-item">
                          <div class="post-img" style="background-image: url(${post.photo})"></div>
                          <div class="post-date">${date}</div>
                          <div class="post-content-wrapper">
                            <span class="post-road">${post.roadName}</span>
                            <h3 class="post-title">${post.title}</h3>
                            <p class="post-description">${post.description}</p>
                            <div class="par-wrapper">
                              <div class="post-author">
                                <div class="pa-img"><i class="${authored}pa-img-i fa fa-user-circle-o" aria-hidden="true"></i></div>
                                <span class="pa-name">${post.author}</span>
                              </div>
                              <div id="${post.id}" class="rc-img ${supportClass} likes">
                                <span>${post.support}</span>
                                <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>`;
        return post;
    }
    
    // Event listener for navigation bar scroll
    function initScrollWatching() {
      var mn = $("nav.index-navigation");
      var mns = "index-navigation-scrolled";
      var headerHeight = document.querySelector('header').clientHeight;
      
      var lastScrollTop = 0;      

      window.addEventListener("scroll", (function(mn, mns, headerHeight) {
            return function () {
                var offZone = $(this).scrollTop() > headerHeight;
                if (offZone) {
                    mn.addClass(mns);
                    $('.section-1').css('padding-top', '70px');
                } else {
                    mn.removeClass(mns);
                    $('.section-1').css('padding-top', '0');
                }

                var st = $(this).scrollTop();
                if (st > lastScrollTop && $(this).scrollTop() > headerHeight + 70) {
                    mn.removeClass('top');
                } 
                else {
                    mn.addClass('top');
                }
                lastScrollTop = st;
            };
      })(mn, mns, headerHeight));
    }
})();


