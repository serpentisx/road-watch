// Javascript file for login page

// Event listener to toggle between login, forgot password and sign-up panel
$('.message a').click(function () {
    var c = "." + $(this).attr("class").split(' ')[0];
    $('form').animate({height: "hide", opacity: "hide"}, "slow");
    $(c).animate({height: "show", opacity: "show"}, "slow");
});