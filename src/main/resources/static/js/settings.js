$('.as-label').click(function() {
  var classname = $(this).attr("class").split(' ')[1];
  $('.main__settings-form').css('display', 'none');
  $(`.${classname}`).css('display', 'block');
  $('.as-label').css('background', '#fafbfb');
  $(this).css('background', '#8ed081');
  $('.message').text("");
});

$('.sidebar__menu-item').click(function() {
  $('.sidebar__menu-item').removeClass('sidebar__menu-item--active');
  $(this).addClass('sidebar__menu-item--active');
  $('.message').text("");
});