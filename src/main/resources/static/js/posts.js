
function addButtonListeners() {
  var buttons = document.querySelectorAll('button.new-entry-toggle');
  for (var i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener('click', function () {
      console.log('clicked');
      var newEntryContainer = document.querySelector('.new-entry-container');
      var posts = document.querySelector('.posts');
      var button = document.querySelector('button.fixed');
      if (!newEntryContainer.style.display || newEntryContainer.style.display === 'none') {
        newEntryContainer.style.display = 'flex';
        posts.style.display = 'none';
        button.style.display = 'none';
      } else {
        newEntryContainer.style.display = 'none';
        posts.style.display = 'flex';
        button.style.display = 'block';
      }
    });
  }
}

document.addEventListener('DOMContentLoaded', function () {
  addButtonListeners();
});