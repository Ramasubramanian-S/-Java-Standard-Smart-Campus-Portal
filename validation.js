$(document).ready(function () {
  function showError(id, message) {
    const input = $('#' + id);
    input.addClass('is-invalid');
    input.next('.invalid-feedback').text(message).show();
  }

  function clearError(id) {
    const input = $('#' + id);
    input.removeClass('is-invalid');
    input.next('.invalid-feedback').hide();
  }

  function validateName() {
    const val = $('#name').val().trim();
    if (!val) { showError('name', 'Name is required'); return false; }
    clearError('name'); return true;
  }

  function validateEmail() {
    const email = $('#email').val().trim();
    const pattern = /^\S+@\S+\.\S+$/;
    if (!email) { showError('email', 'Email is required'); return false; }
    if (!pattern.test(email)) { showError('email', 'Enter valid email'); return false; }
    clearError('email'); return true;
  }

  function validatePassword() {
    const pw = $('#password').val();
    if (pw.length < 6) { showError('password', 'Min 6 characters'); return false; }
    clearError('password'); return true;
  }

  function validateConfirmPassword() {
    const pw = $('#password').val();
    const cpw = $('#confirmPassword').val();
    if (pw !== cpw) { showError('confirmPassword', 'Passwords do not match'); return false; }
    clearError('confirmPassword'); return true;
  }

  function validatePhone() {
    const phone = $('#phone').val().trim();
    const pattern = /^\d{10}$/;
    if (!pattern.test(phone)) { showError('phone', 'Enter 10-digit number'); return false; }
    clearError('phone'); return true;
  }

  function validateDOB() {
    const dob = $('#dob').val();
    if (!dob) { showError('dob', 'DOB required'); return false; }
    clearError('dob'); return true;
  }

  function validateGender() {
    if (!$('input[name="gender"]:checked').val()) {
      $('#genderError').show(); return false;
    }
    $('#genderError').hide(); return true;
  }

  function validateDepartment() {
    const dept = $('#department').val().trim();
    if (!dept) { showError('department', 'Department is required'); return false; }
    clearError('department'); return true;
  }

  function validateYear() {
    const year = $('#year').val();
    if (!year) { showError('year', 'Select year'); return false; }
    clearError('year'); return true;
  }

  function validatePhoto() {
    const photo = $('#photo').val();
    if (!photo) { showError('photo', 'Upload photo'); return false; }
    clearError('photo'); return true;
  }

  function validateAddress() {
    const address = $('#address').val().trim();
    if (!address) { showError('address', 'Address required'); return false; }
    clearError('address'); return true;
  }

  $('#registrationForm').submit(function (e) {
    e.preventDefault();
    let valid = true;

    if (!validateName()) valid = false;
    if (!validateEmail()) valid = false;
    if (!validatePassword()) valid = false;
    if (!validateConfirmPassword()) valid = false;
    if (!validatePhone()) valid = false;
    if (!validateDOB()) valid = false;
    if (!validateGender()) valid = false;
    if (!validateDepartment()) valid = false;
    if (!validateYear()) valid = false;
    if (!validatePhoto()) valid = false;
    if (!validateAddress()) valid = false;

    if (valid) {
      $('#successModal').modal('show');
      $('#registrationForm')[0].reset();
      $('.invalid-feedback').hide();
      $('.is-invalid').removeClass('is-invalid');
      $('#genderError').hide();
    }
  });

  // Live field validation
  $('#name').on('input', validateName);
  $('#email').on('input', validateEmail);
  $('#password').on('input', function () {
    validatePassword(); validateConfirmPassword();
  });
  $('#confirmPassword').on('input', validateConfirmPassword);
  $('#phone').on('input', validatePhone);
  $('#dob').on('change', validateDOB);
  $('input[name="gender"]').on('change', validateGender);
  $('#department').on('input', validateDepartment);
  $('#year').on('change', validateYear);
  $('#photo').on('change', validatePhoto);
  $('#address').on('input', validateAddress);
});

  
