document.addEventListener("DOMContentLoaded", function(){
    var $root = $('html, body');
    
    initSupportPostListener();

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
    
    function initSupportPostListener() {
        $('.rc-img').off();
        $('.rc-img').click(function () {
            var that = this;

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
                    data: JSON.stringify(id),
                });
            });
        });
    }
    
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
    
    function isLoggedIn() {
        return Promise.resolve($.ajax({
            type: "POST",
            context: this,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: "/isLoggedIn",
        }))
    }

    function generatePostElement(post) {
        var date = post.date ? post.date : "";
        var supportClass = post.isSupporting ? "rc-img-active" : "rc-img likes";
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
                                <div class="pa-img"></div>
                                <span class="pa-name">${post.author}</span>
                              </div>
                              <div id="${post.id}" class="rc-img ${supportClass} likes">
                                <span>${post.support}</span>
                                <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>`
        return post;
    }
});