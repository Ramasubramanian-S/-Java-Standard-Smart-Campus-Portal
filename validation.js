$(document).ready(function() {
  $("#registrationForm").submit(function(e) {
    let name = $("#name").val().trim();
    let email = $("#email").val().trim();
    let password = $("#password").val().trim();
    let confirmPassword = $("#confirmPassword").val().trim();

    if (name === "") {
      alert("Name is required");
      e.preventDefault();
    } else if (email === "" || !email.includes("@")) {
      alert("Valid email is required");
      e.preventDefault();
    } else if (password.length < 6) {
      alert("Password must be at least 6 characters");
      e.preventDefault();
    } else if (password !== confirmPassword) {
      alert("Passwords do not match");
      e.preventDefault();
    }
  });
});
