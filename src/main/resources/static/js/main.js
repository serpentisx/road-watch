'use strict';

$(document).ready(function () {

    /**************************  Login / Register Form  ***************************/

    $('.message a').click(function () {
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });

    $('.mobile-label').click(function () {
        if (document.getElementById('mobile-check').checked) {
            $('body').css('overflow', 'inherit');
        } else {
            $('body').css('overflow', 'hidden');
            console.log("sdfds");
        }
    });

});