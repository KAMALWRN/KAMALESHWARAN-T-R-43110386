const box = document.getElementById('box');
const marginInput = document.getElementById('margin');
const borderInput = document.getElementById('border');
const paddingInput = document.getElementById('padding');

const marginValue = document.getElementById('marginValue');
const borderValue = document.getElementById('borderValue');
const paddingValue = document.getElementById('paddingValue');

marginInput.addEventListener('input', () => {
  box.style.margin = marginInput.value + 'px';
  marginValue.textContent = marginInput.value + 'px';
});

borderInput.addEventListener('input', () => {
  box.style.borderWidth = borderInput.value + 'px';
  borderValue.textContent = borderInput.value + 'px';
});

paddingInput.addEventListener('input', () => {
  box.style.padding = paddingInput.value + 'px';
  paddingValue.textContent = paddingInput.value + 'px';
});