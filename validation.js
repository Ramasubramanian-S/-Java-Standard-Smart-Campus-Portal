$(document).ready(function () {
  function showError(inputId, message) {
    const input = $('#' + inputId);
    input.addClass('is-invalid');
    input.next('.invalid-feedback').text(message).show();
  }

  function clearError(inputId) {
    const input = $('#' + inputId);
    input.removeClass('is-invalid');
    input.next('.invalid-feedback').hide();
  }

  function validateName() {
    const name = $('#name').val().trim();
    if (name === '') {
      showError('name', 'Name is required');
      return false;
    }
    clearError('name');
    return true;
  }

  function validateEmail() {
    const email = $('#email').val().trim();
    const emailPattern = /^\S+@\S+\.\S+$/;
    if (email === '') {
      showError('email', 'Email is required');
      return false;
    } else if (!emailPattern.test(email)) {
      showError('email', 'Enter a valid email');
      return false;
    }
    clearError('email');
    return true;
  }

  function validatePassword() {
    const password = $('#password').val();
    if (password.length < 6) {
      showError('password', 'Password must be at least 6 characters');
      return false;
    }
    clearError('password');
    return true;
  }

  function validateConfirmPassword() {
    const password = $('#password').val();
    const confirmPassword = $('#confirmPassword').val();
    if (password !== confirmPassword) {
      showError('confirmPassword', 'Passwords do not match');
      return false;
    }
    clearError('confirmPassword');
    return true;
  }

  // On form submit
  $('#registrationForm').submit(function (event) {
    let valid = true;
    if (!validateName()) valid = false;
    if (!validateEmail()) valid = false;
    if (!validatePassword()) valid = false;
    if (!validateConfirmPassword()) valid = false;

    if (!valid) {
      event.preventDefault(); // Stop form from submitting
    }
  });

  // Optional: live validation
  $('#name').on('input', validateName);
  $('#email').on('input', validateEmail);
  $('#password').on('input', function () {
    validatePassword();
    validateConfirmPassword();
  });
  $('#confirmPassword').on('input', validateConfirmPassword);
});


