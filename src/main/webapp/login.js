/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
const loginText = document.querySelector(".title-text .login");
const loginForm = document.querySelector("form.login");
const loginBtn = document.querySelector("label.login");
const signupBtn = document.querySelector("label.signup");
const signupLink = document.querySelector("form .signup-link a");
signupBtn.onclick = (() => {
    loginForm.style.marginLeft = "-50%";
    loginText.style.marginLeft = "-50%";
});
loginBtn.onclick = (() => {
    loginForm.style.marginLeft = "0%";
    loginText.style.marginLeft = "0%";
});
signupLink.onclick = (() => {
    signupBtn.click();
    return false;
});
function switchToSignUp() {
    loginForm.style.marginLeft = "-50%";
    loginText.style.marginLeft = "-50%";
}

// Check the URL when the page loads
window.onload = (() => {
    const currentUrl = window.location.href;
    if (currentUrl.includes("signup")) {
        switchToSignUp();
    }
});
document.addEventListener('DOMContentLoaded', function () {

    document.querySelector('.signup').addEventListener('submit', function (e) {
        // Prevent form submission until validation is complete
        e.preventDefault();

        var name = document.getElementById('sign-up-name').value;
        var email = document.getElementById('sign-up-email').value;
        var password = document.getElementById('sign-up-password').value;
        var rePassword = document.getElementById('sign-up-cf-password').value;
        var isValid = true;
        var errorMessage = '';

        // Name validation
        if (name.trim() === '') {
            errorMessage = 'Họ và tên không được để trống.';
            isValid = false;
        }

        // Email validation
        else if (!validateEmail(email)) {
            errorMessage = 'Địa chỉ Email không hợp lệ.';
            isValid = false;
        }

        // Password validation
        else if (password.length < 6) {
            errorMessage = 'Mật khẩu phải có ít nhất 6 ký tự.';
            isValid = false;
        }

        // Re-password validation
        else if (password !== rePassword) {
            errorMessage = 'Mật khẩu nhập lại không khớp.';
            isValid = false;
        }

        // Update message and submit form if valid
        document.getElementById('message-validate').textContent = errorMessage;
        if (isValid) {
            e.target.submit();
        }
    });

    // Real-time validation for re-password
    document.getElementById('sign-up-cf-password').addEventListener('keyup', function () {
        var password = document.getElementById('sign-up-password').value;
        var rePassword = this.value;

        if (password !== rePassword) {
            document.getElementById('message-validate').textContent = 'Mật khẩu nhập lại không khớp.';
        } else {
            document.getElementById('message-validate').textContent = '';
            document.getElementById('message-return').textContent = '';
        }
    });

    // Email regex validation function
    function validateEmail(email) {
        var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(String(email).toLowerCase());
    }
});