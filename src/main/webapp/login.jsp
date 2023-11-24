<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : login
    Created on : Sep 22, 2023, 3:20:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script
            src="https://kit.fontawesome.com/64d58efce2.js"
            crossorigin="anonymous"
    ></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <title>Login</title>
</head>
<style> @import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

html, body {
    display: grid;
    height: 100%;
    width: 100%;
    place-items: center;
    background: linear-gradient(90deg, rgba(2, 0, 36, 1) 0%, rgba(88, 140, 126, 1) 35%);
}

::selection {
    background: #1a75ff;
    color: #fff;
}

.wrapper {
    overflow: hidden;
    max-width: 390px;
    background: #fff;
    padding: 30px;
    border-radius: 15px;
    box-shadow: 0px 15px 20px rgba(0, 0, 0, 0.1);
}

.wrapper .title-text {
    display: flex;
    width: 200%;
}

.wrapper .title {
    width: 50%;
    font-size: 35px;
    font-weight: 600;
    text-align: center;
    transition: all 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .slide-controls {
    position: relative;
    display: flex;
    height: 50px;
    width: 100%;
    overflow: hidden;
    margin: 30px 0 10px 0;
    justify-content: space-between;
    border: 1px solid lightgrey;
    border-radius: 15px;
}

.slide-controls .slide {
    height: 100%;
    width: 100%;
    color: #fff;
    font-size: 18px;
    font-weight: 500;
    text-align: center;
    line-height: 48px;
    cursor: pointer;
    z-index: 1;
    transition: all 0.6s ease;
}

.slide-controls label.signup {
    color: #000;
}

.slide-controls .slider-tab {
    position: absolute;
    height: 100%;
    width: 50%;
    left: 0;
    z-index: 0;
    border-radius: 15px;
    background: linear-gradient(90deg, rgba(2, 0, 36, 1) 0%, rgba(88, 140, 126, 1) 35%);
    transition: all 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

input[type="radio"] {
    display: none;
}

#signup:checked ~ .slider-tab {
    left: 50%;
}

#signup:checked ~ label.signup {
    color: #fff;
    cursor: default;
    user-select: none;
}

#signup:checked ~ label.login {
    color: #000;
}

#login:checked ~ label.signup {
    color: #000;
}

#login:checked ~ label.login {
    cursor: default;
    user-select: none;
}

.wrapper .form-container {
    width: 100%;
    overflow: hidden;
}

.form-container .form-inner {
    display: flex;
    width: 200%;
}

.form-container .form-inner form {
    width: 50%;
    transition: all 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.form-inner form .field {
    height: 50px;
    width: 100%;
    margin-top: 20px;
}

.form-inner form .field input {
    height: 100%;
    width: 100%;
    outline: none;
    padding-left: 15px;
    border-radius: 15px;
    border: 1px solid lightgrey;
    border-bottom-width: 2px;
    font-size: 17px;
    transition: all 0.3s ease;
}

.form-inner form .field input:focus {
    border-color: #1a75ff;
    /* box-shadow: inset 0 0 3px #fb6aae; */
}

.form-inner form .field input::placeholder {
    color: #999;
    transition: all 0.3s ease;
}

form .field input:focus::placeholder {
    color: #1a75ff;
}

.form-inner form .pass-link {
    margin-top: 5px;
}

.form-inner form .signup-link {
    text-align: center;
    margin-top: 30px;
}

.form-inner form .pass-link a,
.form-inner form .signup-link a {
    color: rgb(88, 140, 126);
    text-decoration: none;
}

.form-inner form .pass-link a:hover,
.form-inner form .signup-link a:hover {
    text-decoration: underline;
}

form .btn {
    height: 50px;
    width: 100%;
    border-radius: 15px;
    position: relative;
    overflow: hidden;
}

form .btn .btn-layer {
    height: 100%;
    width: 300%;
    position: absolute;
    left: -100%;
    background: linear-gradient(90deg, rgba(2, 0, 36, 1) 0%, rgba(88, 140, 126, 1) 35%);
    border-radius: 15px;
    transition: all 0.4s ease;;
}

form .btn:hover .btn-layer {
    left: 0;
}

form .btn input[type="submit"] {
    height: 100%;
    width: 100%;
    z-index: 1;
    position: relative;
    background: none;
    border: none;
    color: #fff;
    padding-left: 0;
    border-radius: 15px;
    font-size: 20px;
    font-weight: 500;
    cursor: pointer;
}

a {
    text-decoration: none;
    text-align: left;
    color: rgb(88, 140, 126);
}

a:hover {
    text-decoration: underline;
}

.house {
    padding-top: 10px
}

.thank-you-pop {
    width: 100%;
    padding: 20px;
    text-align: center;
}

.thank-you-pop img {
    width: 76px;
    height: auto;
    margin: 0 auto;
    display: block;
    margin-bottom: 25px;
}

.thank-you-pop h1 {
    font-size: 42px;
    margin-bottom: 25px;
    color: #5C5C5C;
}

.thank-you-pop p {
    font-size: 20px;
    margin-bottom: 27px;
    color: #5C5C5C;
}

.thank-you-pop h3.cupon-pop {
    font-size: 25px;
    margin-bottom: 40px;
    color: #222;
    display: inline-block;
    text-align: center;
    padding: 10px 20px;
    border: 2px dashed #222;
    clear: both;
    font-weight: normal;
}

.thank-you-pop h3.cupon-pop span {
    color: #03A9F4;
}

.thank-you-pop a {
    display: inline-block;
    margin: 0 auto;
    padding: 9px 20px;
    color: #fff;
    text-transform: uppercase;
    font-size: 14px;
    background-color: #8BC34A;
    border-radius: 17px;
}

.thank-you-pop a i {
    margin-right: 5px;
    color: #fff;
}
</style>
<body>
<div class="wrapper">
    <div class="title-text">
        <div class="title login">DN BUS</div>
        <div class="title signup">DN BUS</div>
    </div>
    <div class="form-container">
        <div class="slide-controls">
            <input type="radio" name="slide" id="login" checked>
            <input type="radio" name="slide" id="signup">
            <label for="login" class="slide login">Đăng nhập</label>
            <label for="signup" class="slide signup">Đăng kí</label>
            <div class="slider-tab"></div>
        </div>
        <div class="form-inner">
            <form action="LoginControl" class="login" method="POST">
                <div class="field">
                    <input type="text" name="email" type="email" placeholder="Địa chỉ Email" required>
                </div>
                <div class="field">
                    <input type="password" name="password" placeholder="Mật khẩu" required>
                </div>
                <p class="text-danger" style="color:red">${mess}</p>
                <div class="pass-link"><a href="forgotpassword.jsp">Quên mật khẩu?</a></div>
                <div class="field btn">
                    <div class="btn-layer"></div>
                    <input type="submit" value="Đăng nhập">
                </div>
                <div class="signup-link">Chưa có tài khoản? <a href="">Đăng kí ngay</a></div>
                <div class="house"><a href="index.jsp"><i class="fas fa-home"></i>Trở về trang chủ</a></div>
            </form>
            <form action="signup" class="signup" method="POST">
                <div class="field w-100">
                    <input name="name" type="text" placeholder="Họ và tên" id="sign-up-name" value="${name}" required>
                </div>
                <div class="field w-100">
                    <input name="email" type="email" placeholder="Địa chỉ Email" id="sign-up-email" value="${email}"
                           required>
                </div>
                <div class="field">
                    <input name="password" type="password" placeholder="Mật khẩu" id="sign-up-password"
                           value="${password}" required>
                </div>
                <div class="field">
                    <input name="re-password" type="password" placeholder="Nhập lại mật khẩu" id="sign-up-cf-password"
                           value="${repassword}" required>
                </div>
                <p class="text-danger" style="color:red" id="message-return">${mess3}</p>
                <p class="text-danger" style="color:red" id="message-validate">${mess2}</p>
                <div class="field btn">
                    <div class="btn-layer"></div>
                    <input type="submit" value="Đăng kí">
                </div>
                <div class="house"><a href="index.jsp"><i class="fas fa-home"></i>Trở về trang chủ</a></div>
            </form>
        </div>
    </div>
</div>
<c:if test="${sessionScope.msgActive ne null}">
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Thông báo</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="thank-you-pop">
                        <img src="http://goactionstations.co.uk/wp-content/uploads/2017/03/Green-Round-Tick.png" alt="">
                        <p>${sessionScope.msgActive}</p>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        const myModal = new bootstrap.Modal(document.getElementById('exampleModal'))
        myModal.show();
    </script>
    <c:remove var="msgActive" scope="session"></c:remove>
</c:if>
<c:if test="${msgActive ne null}">
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Thông báo</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="thank-you-pop">
                        <img src="http://goactionstations.co.uk/wp-content/uploads/2017/03/Green-Round-Tick.png" alt="">
                        <p>${msgActive}</p>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        const myModal = new bootstrap.Modal(document.getElementById('exampleModal'))
        myModal.show();
    </script>
</c:if>
<script src="login.js"></script>

</body>
</html>