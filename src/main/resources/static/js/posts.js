var button = document.querySelector('button.back');

button.addEventListener('click', function () {
  console.log('clicked');
  var container = document.querySelector('.new-entry-container');
  var label = document.querySelector('label[for="new-entry"]');
  var checkbox = document.querySelector('.new-entry-checkbox');
  container.style.display = 'none';
  label.style.display = 'block';
  checkbox.checked = false;
  console.log(checkbox.checked);
});
