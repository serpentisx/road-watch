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