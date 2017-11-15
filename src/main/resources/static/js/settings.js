
(() => {
    // Event listener for side bar clicking
    (function initSideBarListener() {
        $('.sidebar__menu-item').click(function () {
            $('.sidebar__menu-item').removeClass('sidebar__menu-item--active');
            $(this).addClass('sidebar__menu-item--active');

            $('.main > div').addClass('main__hidden');
            var tabReference = $(this).attr("class").split(' ')[1];
            $('.' + tabReference + '__tab').removeClass('main__hidden');

            $('.message').text("");
        });
    })();

    // Event listener for tab-clicking
    (function initTabListener() {
        $('.as-label').click(function () {
            var classname = $(this).attr("class").split(' ')[1];
            $('.main__settings-form').css('display', 'none');
            $(`.${classname}`).css('display', 'block');
            $('.as-label').css('background', '#fafafa');
            $(this).css('background', '#8ed081');

            $('.message').text("");
        });
    })();

    // Adding event handlers to the delete-post anchors
    var deletePostButtons = document.querySelectorAll('a.delete-post');

    for (var i = 0; i < deletePostButtons.length; i++) {
        deletePostButtons[i].addEventListener('click', deletePostHandler);
    }
    
     /**
     * Adding event handlers to the archive-post anchors
     *
     * Handles archive post requests.
     * Sends a request with the post id to server,
     * and handles the response appropriately.
     * 
     * return true if the post has been archived,
     *        false if the post has been un-archived
     */
    $('.archive-post-btn').click(function () {
        var postID = parseInt($('i', this).attr('class').split(" ")[0]);
        var that = this;
        $.ajax({
            type: "POST",
            context: that,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: "/archivePost",
            data: JSON.stringify(postID),
            success: function(res) {
                if (res) {
                    $('i', that).addClass('archived fa-check-circle-o').removeClass('fa-circle');
                }
                else {
                    $('i', that).addClass('fa-circle').removeClass('archived fa-check-circle-o');
                }
            }
        });
    });
    
    
    /**
     * Handles delete post requests.
     * Sends a request with the post id to server,
     * and handles the response appropriately.
     * 
     * @param {type} e --> the event object
     * @returns {undefined}
     */
    function deletePostHandler(e) {
        if (confirm('Ertu viss um að þú viljir eyða innleginu þínu?')) {
            var postId = e.target.classList[0];
            var url = '/delete-post';

            var request = new XMLHttpRequest();
            request.open('POST', '/delete-post', true);

            request.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

            function showMessage(msg) {
                var messageEl = document.querySelector('.message');
                messageEl.appendChild(document.createTextNode(msg));
            }

            request.onload = function () {
                if (request.status >= 200 && request.status < 400) {
                    // Beiðni árangursrík
                    if (request.response) {
                        showMessage('Það tókst að eyða innlegginu þínu');
                    } else {
                        showMessage('Eitthvað fór úrskeiðis og ekki tókst að eyða innlegginu þínu');
                    }
                } else {
                    showMessage('Vefþjónn skilaði villu, reyndu aftur síðar.');
                }
            };

            request.onerror = function () {
                showMessage('Óþekkt villa');
            };

            request.send(JSON.stringify(postId));
        }
    }
})();
