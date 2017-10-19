document.addEventListener("DOMContentLoaded", function(){
    var $root = $('html, body');

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

    $('.p-see-more-btn').click(function () {
        var next = posts.splice(0, 6);
        for (var i = 0; i < next.length; i++) {
           $('.posts-container').append(generatePostElement(next[i]));
        }
        if (posts.length === 0) {
            $('.p-see-more-btn').remove();
        }
    })

    function generatePostElement(post) {
        var date = post.date ? post.date : "";
        var post =  `<div class="post-item-wrapper">
                        <div class="post-item">
                          <div class="post-img" style="background-image: url(${post.photo})"></div>
                          <div class="post-date">${date}</div>
                          <div class="post-content-wrapper">
                            <span class="post-road">${post.road}</span>
                            <h3 class="post-title">${post.title}</h3>
                            <p class="post-description">${post.description}</p>
                            <div class="par-wrapper">
                              <div class="post-author">
                                <div class="pa-img"></div>
                                <span class="pa-name">${post.author}</span>
                              </div>
                              <div class="rc-img likes">
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