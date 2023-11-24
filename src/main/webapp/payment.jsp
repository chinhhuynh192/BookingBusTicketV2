<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.busticketbooking.busticketbooking.models.Booking" %>
<%@ page import="com.busticketbooking.busticketbooking.models.User" %>
<%@ page import="com.busticketbooking.busticketbooking.models.Trip" %>
<%@ page import="com.busticketbooking.busticketbooking.Utils.ConvertUtil" %>
<%@ page import="com.busticketbooking.busticketbooking.Utils.AttrWrapper" %>
<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/15/2023
  Time: 1:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="themify-icons/themify-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
            integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <link rel="stylesheet" href="css/style.css">
    <title>Payment</title>
</head>
<body>
<%
    Booking booking = (Booking) request.getAttribute("booking");
    if(booking == null){
        response.sendRedirect("index.jsp");
    }
    User user = (User) request.getAttribute("user");
    Trip trip = (Trip) request.getAttribute("trip");
%>
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-sm-10"></div>
            <div class="col-sm-2">

                <c:if test="${sessionScope.acc != null}">
                <div class="log-out"><a href="<%=request.getContextPath()%>/profile"><i
                        class="fas fa-user fa-sm">${sessionScope.acc.phone}</i></a></div>
                <div class="icon-logout"><a href="Logout"><i class="fas fa-sign-out-alt fa-lg"></i></a>
                    </c:if>
                    <c:if test="${sessionScope.acc == null}">
                        <div class="sign-up"><a href="login.jsp"><i class="fas fa-user fa-sm">Đăng kí/Đăng nhập</i></a>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <a href="index.jsp"><img src="image/logo.png" class="logo"></a>
                    <div class="home"><a href="">Trang chủ </a></div>
                    <div class="search-ticket"><a href="">Tra cứu vé</a></div>
                    <div class="new"><a href="">Tin tức</a></div>
                    <div class="contact"><a href="">Liên hệ</a></div>
                    <div class="about-us"><a href="">Về chúng tôi</a></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container mt-2">
    <div class="row">
        <div class="col-3">
            <div class="d-flex w-100 flex-column align-items-start mt-4">
                <form action="/vn-pay" method="post">
                    <div class="nav d-flex flex-column nav-pills me-3 w-100 justify-content-start" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <button class="nav-link active m-2 w-100 border border-dark rounded" id="v-pills-bank-tab"
                                data-bs-toggle="pill" data-bs-target="#v-pills-bank"
                                type="button" role="tab" aria-controls="v-pills-bank" aria-selected="true"><i
                                class="fas fa-money-check me-2"></i>Thanh toán qua tài khoản ngân hàng
                        </button>
                        <button class="nav-link d-flex m-2 w-100 border border-dark rounded" id="v-pills-momo-tab"
                                data-bs-toggle="pill" data-bs-target="#v-pills-momo"
                                type="button" role="tab" aria-controls="v-pills-momo" aria-selected="false"><img
                                src="https://developers.momo.vn/v3/vi/assets/images/icon-52bd5808cecdb1970e1aeec3c31a3ee1.png"
                                alt="momo" class="img-fluid img-thumbnail me-2" style="width: 40px; height: 40px;">Qua momo
                        </button>
                        <button class="nav-link d-flex m-2 w-100 border border-dark rounded" id="v-pills-vn-pay-tab"
                                data-bs-toggle="pill" data-bs-target="#v-pills-vn-pay-tab" name="amount" value="${booking.price - booking.price * booking.discount}"
                                type="submit" role="tab" aria-controls="v-pills-vn-pay-tab" aria-selected="false"><img
                                src="https://downloadlogomienphi.com/sites/default/files/logos/download-logo-vector-vnpay-mien-phi.jpg"
                                alt="momo" class="img-fluid img-thumbnail me-2" style="width: 40px; height: 40px;">Qua VN Pay
                        </button>
                        <input type="hidden" name="bookingId" value="${param.bookingId}"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-4 d-flex flex-column align-items-center justify-content-center">
            <div class="d-flex flex-column align-items-center justify-content-center w-100 mb-2">
                <h5 class="text-secondary fs-3">
                    Tổng thanh toán
                </h5>
                <p class="fs-1 text-warning fw-bold" id="totalPrice"></p>
            </div>
            <div class="d-flex flex-column align-items-center justify-content-center bg-light .bg-gradient text-danger fs-5 w-100">
                <p>Thời gian giữ chỗ còn lại: <span id="time-count"></span></p>
            </div>
            <div class="tab-content p-0" id="v-pills-tabContent">
                <div class="tab-pane fade show active w-100 p-0" id="v-pills-bank" role="tabpanel"
                     aria-labelledby="v-pills-bank-tab">
                    <div class="d-flex flex-column align-items-center justify-content-center w-100 p-0">
                        <div class="w-100 p-0">
                            <img src="https://admin.tamlyvietphap.vn/uploaded/Images/Original/2020/10/16/logo_vietcombank_1610091313.jpg"
                                 alt="Vietcombank" class="img-fluid img-thumbnail">
                        </div>
                        <p class="fs-5">Ngân hàng Vietcombank</p>
                        <p class="fs-3 fw-bold">Tài khoản: <span class="badge bg-light text-dark">01234567890</span></p>
                        <p class="fs-5">Nội dung chuyển khoản: <%=user.getPhone()%>
                        </p>
                    </div>
                </div>
                <div class="tab-pane fade w-100 p-0" id="v-pills-momo" role="tabpanel" aria-labelledby="v-pills-bank-tab">
                    <div class="d-flex flex-column align-items-center justify-content-center w-100 p-0">
                        <div class="w-100 p-0">
                            <img src="https://homepage.momocdn.net/img/momo-upload-api-221123171214-638048203345354232.png"
                                 alt="Vietcombank" class="img-fluid img-thumbnail">
                        </div>
                        <p class="fw-bold fs-5">Momo</p>
                        <p class="fs-4">Nội dung chuyển khoản: <%=user.getPhone()%>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-4 d-flex flex-column align-items-center justify-content-start">
            <div class="d-flex flex-column align-items-center border border-2 shadow-sm p-3 mb-2 bg-body rounded w-100 mt-2">
                <div class="d-flex align-items-start mt-2 flex-column w-100">
                    <h4>Thông tin khách hàng</h4>
                    <div class="d-flex w-100">
                        <div class="d-flex flex-column align-items-start text-secondary fs-5">
                            <p class="mb-1">Họ và tên</p>
                            <p class="mb-1">Số điện thoại</p>
                            <p class="mb-1">Email</p>
                        </div>
                        <div class="d-flex flex-column align-items-end text-dark fs-5 flex-grow-1">
                            <p class="mb-1"><%=user.getName()%>
                            </p>
                            <p class="mb-1"><%=user.getPhone()%>
                            </p>
                            <p class="mb-1"><%=user.getEmail()%>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-column align-items-center border border-2 shadow-sm p-3 mb-2 bg-body rounded w-100 mt-2">
                <div class="d-flex align-items-start mt-2 flex-column w-100">
                    <h4>Thông tin lượt đi</h4>
                    <div class="d-flex w-100">
                        <div class="d-flex flex-column align-items-start text-secondary fs-5">
                            <p class="mb-1">Tuyến xe</p>
                            <p class="mb-1">Thời gian</p>
                            <p class="mb-1">Số lượng ghế</p>
                            <p class="mb-1">Số ghế</p>
                            <p class="mb-1">Tổng tiền lượt đi</p>
                        </div>
                        <div class="d-flex flex-column align-items-end text-dark fs-5 flex-grow-1">
                            <p class="mb-1 text-nowrap"><%=trip.getRoute().getOrigin()%>
                                => <%=trip.getRoute().getDestination()%>
                            </p>
                            <p class="mb-1 text-success"><%=ConvertUtil.formatTime(trip.getTime())%> <%=ConvertUtil.formatDate(trip.getDate())%>
                            </p>
                            <p class="mb-1"><%=ConvertUtil.castStringListToList(booking.getSeatNumber()).size()%> ghế</p>
                            <p class="mb-1" id="numberOfSeat"><%=booking.getSeatNumber()%>
                            </p>
                            <p class="mb-1" id="totalMoneyGo"></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-column align-items-center border border-2 shadow-sm p-3 mb-2 bg-body rounded w-100 mt-2">
                <div class="d-flex align-items-start mt-2 flex-column w-100 ">
                    <h4>Chi tiết giá</h4>
                    <div class="d-flex w-100">
                        <div class="d-flex flex-column align-items-start text-secondary fs-5 justify-content-start">
                            <p class="mb-1">Giá vé lượt đi</p>
                            <p class="mb-1">Ưu đãi</p>
                            <p class="mb-1">Tổng tiền</p>
                        </div>
                        <div
                                class="d-flex flex-column align-items-end text-dark fs-5 justify-content-end flex-grow-1">
                            <p class="mb-1 text-danger-emphasis" id="priceGo"></p>
                            <p class="mb-1 text-success" data-discount="<%=booking.getDiscount()%>" id="discount"></p>
                            <p class="mb-1" id="totalMoney"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <p>Mang đến trải nghiệm tuyệt vời</p>

    <div class="grid-container1">
        <div class="grid-item1">
            <ul>
                <li>
                    <a href="http://127.0.0.1:5500/BookingTicket/html/index.jsp">Liên hệ với chúng tôi</a>

                </li>
                <li>Địa chỉ các văn phòng nhà xe:</li>
                <li>300 Lê Thạch, Đà Nẵng</li>
                <li>1002 Âu Cơ, P14, Tân Bình, TP.HCM</li>
                <li>104A Hoa Bằng, Cầu Giấy, Hà Nội</li>
                <li>Hotline: 028.6275.9999 - 028.6275.8888</li>
                <li>Zalo/SMS: 0986.000.333 - 0967.800.800 - 0934.850.850</li>
                <li>Email: info@NhaxeDN.com</li>
            </ul>
        </div>
        <div class="grid-item1">
            <ul>

                <li><a href="http://127.0.0.1:5500/BookingTicket/html/index.jsp">Đăng ký nhận tin</a></li>
                <li>ĐĂNG KÝ NHẬN BẢN TIN KHUYẾN MÃI</li>
                <li>
                    <form>
                        <input placeholder="Nhập địa chỉ email"/>
                        <div class="register">
                            <button><a href="http://127.0.0.1:5500/BookingTicket/html/index.jsp">Đăng ký</a></button>
                        </div>

                    </form>
                </li>
            </ul>
        </div>
    </div>
</footer>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <p id="text-noti"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="window.location.href='index.jsp'">Đã hiểu</button>
            </div>
        </div>
    </div>
</div>
<%
    AttrWrapper<String> attr = (AttrWrapper<String>) request.getSession(false).getAttribute("booking");
    Booking booking1 = new Gson().fromJson(attr.getValue(), Booking.class);
%>
<script>
    function getWebSocketServer() {
        var protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
        var host = window.location.host;
        var contextPath = '<%=request.getContextPath()%>'; // adjust if necessary
        var endpoint = '/booking/';

        return protocol + host + contextPath + endpoint;

    }
    var bookingId = '<%=booking1.getBookingId()%>' ;
    const userWs = new WebSocket(getWebSocketServer() + bookingId);

    userWs.onmessage = function(event) {
        const exampleModal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
        document.getElementById('text-noti').innerText = event.data
        exampleModal.show();

    };
    calculateTotalPrice();
    startCountdown('<%=request.getAttribute("timeLeft")%>')

    function formatPrice(value) {
        // Convert the value to an integer by truncating the decimal part
        let intValue = Math.trunc(value);

        // Format the integer value with dots for every group of three digits
        let formattedValue = intValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");

        console.log(formattedValue)
        return formattedValue + 'đ';
    }

    function calculateTotalPrice() {
        document.getElementById("totalMoneyGo").innerText = formatPrice('<%=booking.getPrice()%>');
        document.getElementById("priceGo").innerText = formatPrice('<%=booking.getPrice()%>');
        var priceGo = Math.trunc('<%=booking.getPrice()%>')
        var discount = document.getElementById('discount')
        var dataDiscount = discount.getAttribute('data-discount');
        var discountValue = priceGo * dataDiscount
        document.getElementById('discount').innerText = formatPrice(discountValue);
        document.getElementById('totalMoney').innerText = formatPrice(priceGo - discountValue)
        document.getElementById('totalPrice').innerText = formatPrice(priceGo - discountValue)
    }
    function startCountdown(milliseconds) {
        let remainingTime = milliseconds;

        const interval = setInterval(() => {
            // Calculate minutes and seconds
            const minutes = Math.floor(remainingTime / (60 * 1000));
            const seconds = Math.floor((remainingTime % (60 * 1000)) / 1000);

            // Display the remaining time
            document.getElementById('time-count').innerText = `\${String(minutes).padStart(2, '0')}:\${String(seconds).padStart(2, '0')}`;

            // Decrement the remaining time by 1 second
            remainingTime -= 1000;

            // Stop the interval when the time runs out
            if (remainingTime < 0) {
                clearInterval(interval);
                if(confirm("Đã hết hạn thanh toán")){
                    window.location.href ="booking-ticket?tripId=" +'<%=trip.getTripId()%>'
                }
            }
        }, 1000);
    }
</script>
</body>
</html>
