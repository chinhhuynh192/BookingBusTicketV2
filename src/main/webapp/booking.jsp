<%@ page import="com.busticketbooking.busticketbooking.models.Trip" %>
<%@ page import="com.busticketbooking.busticketbooking.models.Booking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.busticketbooking.busticketbooking.Utils.ConvertUtil" %>
<%@ page import="com.busticketbooking.busticketbooking.models.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Test</title>
</head>
<body>
<%
    Trip trip = (Trip) request.getAttribute("trip");
    List<Booking> bookingList = (List) request.getAttribute("bookingList");
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
<main class="container mt-5">
    <div class="row align-items-start">
        <div class="col-7 d-flex flex-column align-items-start">
            <div
                    class="d-flex flex-column align-items-start mt-2 mb-2 border border-2 p-3 shadow-sm p-3 bg-body rounded w-100">
                <div class="d-flex align-items-center w-50 justify-content-between mt-2">
                    <div>
                        <h4>Chọn ghế</h4>
                    </div>
                    <div>
                        <a href="" class="link-primary">Thông tin xe</a>
                    </div>
                </div>
                <div class="d-flex justify-content-between gap-3 mt-3">
                    <div class="d-flex flex-column">
                        <table>
                            <tbody>
                            <%for (int i = 1; i <= trip.getQuantity() / 2; i++) {%>
                            <%if (i % 3 == 1) {%>
                            <tr class="d-flex justify-content-between align-items-center gap-3">
                                <%}%>
                                <td class="position-relative">
                                    <%
                                        int tempVar = i;
                                        if (ConvertUtil.getAllSeatNumbers(bookingList).stream().anyMatch(sn -> sn.intValue() == tempVar)) {%>
                                    <img width="48" src="images/seat_disabled.svg" class="img-fluid"
                                         alt="seat-icon">
                                    <%} else {%>
                                    <img width="48" src="images/seat_active.svg" class="img-fluid"
                                         alt="seat-icon">
                                    <%}%>
                                    <span
                                            class="position-absolute top-50 start-50 translate-middle fw-bold user-select-none"><%=i%>
                                        </span>
                                </td>
                                <%if (i % 3 == 0) {%>
                            </tr>
                            <%}%>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="d-flex flex-column">
                        <table>
                            <tbody>
                            <%for (int i = trip.getQuantity() / 2 + 1; i <= trip.getQuantity(); i++) {%>
                            <%if (i % 3 == 1) {%>
                            <tr class="d-flex justify-content-between align-items-center gap-3">
                                <%}%>
                                <td class="position-relative">
                                    <%
                                        int tempVar = i;
                                        if (ConvertUtil.getAllSeatNumbers(bookingList).stream().anyMatch(sn -> sn.intValue() == tempVar)) {%>
                                    <img width="48" src="images/seat_disabled.svg" class="img-fluid"
                                         alt="seat-icon">
                                    <%} else {%>
                                    <img width="48" src="images/seat_active.svg" class="img-fluid"
                                         alt="seat-icon">
                                    <%}%>
                                    <span
                                            class="position-absolute top-50 start-50 translate-middle fw-bold user-select-none"><%=i%>
                                        </span>
                                </td>
                                <%if (i % 3 == 0) {%>
                            </tr>
                            <%}%>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="ms-4 mt-5 d-flex flex-column gap-3 fs-6 align-items-start">
                                <span class="d-flex align-items-center">
                                    <div class="me-2 p-2 rounded bg-dark bg-opacity-50 ">
                                    </div>Đã bán
                                </span><span class="d-flex align-items-center">
                                    <div class="me-2 p-2 rounded bg-info bg-opacity-50 ">
                                    </div>Còn trống
                                </span><span class="d-flex align-items-center">
                                    <div class="me-2 p-2 rounded bg-danger bg-opacity-75 ">
                                    </div>Đang chọn
                                </span>
                    </div>
                </div>
            </div>
            <div
                    class="d-flex flex-column align-items-start border border-2 p-3 shadow-sm p-3 mb-2 bg-body rounded w-100">
                <div class="d-flex align-items-start mt-2 me-2 align-items-center">
                    <h4>Thông tin đón trả</h4>
                    <a href="#" class="link-primary d-flex">
                                <span class=" ms-2 ti-info-alt" data-bs-toggle="tooltip" data-bs-html="true"
                                      data-bs-title=" <p>Đón/ trả tận nơi:</p>
                                <ul>
                                    <li>- Thời gian nhận khách : Trước 4 tiếng.</li>
                                    <li>- Thời gian xe đón : Chuẩn bị trước 2 -3 tiếng, do mật độ giao thông trong
                                        thành phố
                                        và sẽ kết hợp đón nhiều điểm khác nhau nên thời gian đón cụ thể tài xế sẽ
                                        liên hệ
                                        hẹn giờ.</li>
                                    <li>
                                        - Hẻm nhỏ xe không quay đầu được : Xe trung chuyển sẽ đón Khách đầu hẻm/ đầu
                                        đường.
                                    </li>
                                    <li>
                                        - Khu vực có biển cấm dừng đỗ xe không đón được : Xe trung chuyển sẽ đón tại
                                        vị trí
                                        gần nhất có thể.
                                    </li>
                                    <li>
                                        - Hành lý : Hành lý nhỏ gọn dưới 20 kg, không vận chuyển kèm động vật , thú
                                        cưng.
                                    </li>
                                </ul>">
                                </span>
                    </a>
                </div>
            </div>
            <div class="d-flex align-items-start border border-2 p-2 shadow-sm p-3 mb-5 bg-body rounded w-100">
                <div class="d-flex align-items-start mt-2 flex-column">
                    <h4>Thông tin khách hàng</h4>
                    <form method="POST" action="/vn-pay" class="was-validated" novalidate>
                        <div class="mt-3 d-flex flex-column align-items-start">
                            <label for="inputName" class="form-label">Họ và tên <span
                                    class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="inputName" value='<c:if test="${sessionScope.acc ne null}">${sessionScope.acc.name}</c:if>' required>
                        </div>
                        <div class="mt-3 d-flex flex-column align-items-start">
                            <label for="inputPhoneNumber" class="form-label">Số điện thoại <span
                                    class="text-danger">*</span></label>
                            <input type="text" pattern="(?:7|0\d|\+94\d)\d{8}" class="form-control"
                                   id="inputPhoneNumber" value='<c:if test="${sessionScope.acc ne null}">${sessionScope.acc.phone}</c:if>' required>
                        </div>
                        <div class="mt-3 d-flex flex-column align-items-start">
                            <label for="inputMail" class="form-label">E-mail <span
                                    class="text-danger">*</span></label>
                            <input type="email" class="form-control" value='<c:if test="${sessionScope.acc ne null}">${sessionScope.acc.email}</c:if>' id="inputMail" required>
                        </div>
                    </form>
                </div>
                <div class="d-flex  mt-2 flex-column align-items-start px-5 w-50">
                    <h4 class="text-warning">Điều khoản & Lưu ý</h4>
                    <ul class="p-0 m-0 mt-2">
                        <li>
                            (*) Quý khách vui lòng có mặt tại bến xuất phát của xe trước ít nhất 30 phút giờ xe khởi
                            hành, mang theo thông báo đã thanh toán vé thành công có chứa mã vé được gửi từ hệ thống
                            FUTA BUS LINE. Vui lòng liên hệ Trung tâm tổng đài 1900 6067 để được hỗ trợ.
                        </li>
                        <li class="mt-2">
                            (*) Nếu quý khách có nhu cầu trung chuyển, vui lòng liên hệ Tổng đài trung chuyển 1900
                            6918 trước khi đặt vé. Chúng tôi không đón/trung chuyển tại những điểm xe trung chuyển
                            không thể tới được.
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="col-5 flex-column">
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
                            <p class="mb-1"><span id="quantityOfSeat"></span> ghế</p>
                            <p class="mb-1" id="numberOfSeat"></p>
                            <p class="mb-1" id="totalMoneyGo" data-price-seat="<%=trip.getRoute().getFare()%>"></p>
                            <input type="hidden" name="tripId" id="tripId" value="<%=trip.getTripId()%>>"> <!-- Assuming trip ID is 1 for this example -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-column align-items-center border border-2 shadow-sm p-3 mb-2 bg-body rounded w-100 mt-2">
                <div class="d-flex align-items-start mt-2 flex-column w-100 ">
                    <h4>Giá vé</h4>
                    <div class="d-flex w-100">
                        <div class="d-flex flex-column align-items-start text-secondary fs-5 justify-content-start">
                            <p class="mb-1">Giá vé lượt đi</p>
                            <p class="mb-1">Ưu đãi</p>
                            <p class="mb-1">Tổng tiền</p>
                        </div>
                        <div
                                class="d-flex flex-column align-items-end text-dark fs-5 justify-content-end flex-grow-1">
                            <p class="mb-1 text-danger-emphasis" id="priceGo"></p>
                            <p class="mb-1 text-success" data-discount="0.05" id="discount"></p>
                            <p class="mb-1" id="totalMoney"></p>
                        </div>
                    </div>
                </div>
            </div>
            <div
                    class="d-flex flex-column align-items-center border border-2 shadow-sm p-3 mb-2 bg-body rounded w-100 mt-2">
                <div class="d-flex align-items-start mt-2 flex-column w-100 ">
                    <h4>Thanh toán</h4>
                    <div class="d-flex w-100">
                        <div class="d-flex flex-column align-items-start text-dark justify-content-start">
                            <p class="mb-1 fs-4" id="totalMoneyNeedPay"></p>
                        </div>
                        <div class="d-flex align-items-end text-dark fs-5 justify-content-end flex-grow-1">
                            <button type="button" class="btn btn-light border border-primary">Huỷ</button>
                            <button type="submit" class="btn btn-success border border-light ms-2" id="payment-btn">Thanh
                                toán
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
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
<script>
    const listSelectingSeat = [];
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
    var cells = document.querySelectorAll("td");

    function updateQuantityOfSeat() {
        document.getElementById('quantityOfSeat').innerText = listSelectingSeat.length
    }

    function updateNumberOfSeat() {
        var numberOfSeat = document.getElementById('numberOfSeat')
        if (listSelectingSeat.length === 0) {
            numberOfSeat.innerHTML = "<br>";
            return;
        }
        numberOfSeat.innerText = getContentListSeatNumber().join(', ')
    }

    function getContentListSeatNumber(){
        var contentInListSelectingSeat = [];
        listSelectingSeat.forEach(e => {
            var contentCell = e.getElementsByTagName('span')[0].innerText
            contentInListSelectingSeat.push(contentCell)
        })
        return contentInListSelectingSeat;
    }
    function updatePriceOfSeat() {
        var priceSeat = document.getElementById('totalMoneyGo')
        var dataPrice = priceSeat.getAttribute('data-price-seat')
        var priceGo = dataPrice * listSelectingSeat.length
        priceSeat.innerText = formatPrice(priceGo)
        document.getElementById('priceGo').innerText = priceSeat.innerText
        var discount = document.getElementById('discount')
        var dataDiscount = discount.getAttribute('data-discount');
        var discountValue = priceGo * dataDiscount
        discount.innerText = formatPrice(discountValue)
        var totalMoney = document.getElementById('totalMoney')
        var totalMoneyCalculate = priceGo - discountValue
        totalMoney.innerText = formatPrice(totalMoneyCalculate);
        document.getElementById('totalMoneyNeedPay').innerText = totalMoney.innerText
    }

    function formatPrice(price) {
        // Convert the price to a string and use a regular expression to add the dots
        const formattedPrice = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
        return formattedPrice + "đ";
    }

    for (var cell of cells) {
        var cellImage = cell.getElementsByTagName('img')[0]
        cell.addEventListener('click', changeSeatStatus)
        if (cellImage.src.indexOf('disabled') != -1) {
            cell.classList.add("cursor-pointer-disabled")
        } else {
            cell.classList.add("cursor-pointer-allow")
        }
    }
    updateQuantityOfSeat();
    updateNumberOfSeat();
    updatePriceOfSeat();

    function changeSeatStatus() {
        var cellImage = this.getElementsByTagName('img')[0]
        if (cellImage.src.indexOf('disabled') != -1) {
            return;
        }
        if (listSelectingSeat.length >= 5 && cellImage.src.indexOf('active') != -1) {
            alert("Bạn chỉ được chọn tối đa 5 chỗ")
            return
        }
        if (cellImage.src.indexOf('active') != -1) {
            cellImage.src = "images/seat_selecting.svg"
            listSelectingSeat.push(this)
            updateQuantityOfSeat();
            updateNumberOfSeat();
            updatePriceOfSeat();
            return;
        }
        if (cellImage.src.indexOf('selecting') != -1) {
            cellImage.src = "images/seat_active.svg"
            listSelectingSeat.pop(this)
            updateQuantityOfSeat();
            updateNumberOfSeat();
            updatePriceOfSeat();
            return;
        }
    }
    const validateEmail = (email) => {
        return email.match(
            /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    };
    const validatePhone = (phone) => {
        return phone.match(
            /(?:7|0\d|\+94\d)\d{8}/
        );
    };
    function validateForm(){
        var phone = document.getElementById('inputPhoneNumber').value
        var email = document.getElementById('inputMail').value
        var name = document.getElementById('inputName').value

        console.log(name);
        if(name.trim().length === 0 || name.trim().length > 50){
            alert("Bạn phải điền tên hoặc tên không quá 50 kí tự")
            return false;
        }
        if(!validateEmail(email)){
            alert("Định dạng email không phù hợp")
            return false;
        }
        if(!validatePhone(phone)){
            alert("Định dạng số điện thoại không phù hợp")
            return false;
        }
        return true
    }
    function SubmitData(){
        var priceSeat = document.getElementById('totalMoneyGo')
        var dataPrice = priceSeat.getAttribute('data-price-seat')
        var priceGo = dataPrice * listSelectingSeat.length
        if(listSelectingSeat.length === 0){
            alert("Bạn phải chọn ghế trước");
            return;
        }
        if(!validateForm()){
            return;
        }
        const tripId = document.getElementById('tripId').value;
        const seatNumbers = getContentListSeatNumber();
        console.log(seatNumbers)
        var user = {
            phone : document.getElementById('inputPhoneNumber').value,
            email : document.getElementById('inputMail').value,
            name : document.getElementById('inputName').value
        }
        const dataToSend = {
            tripId: parseInt(tripId),
            seatNumbers: seatNumbers,
            amount: 2,
            user : user
        };

        // Send a POST request
        fetch('<%=request.getContextPath()%>/booking-ticket', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(dataToSend)
        })
            .then(response => {
                if(response.ok){
                    return response.json();
                }
                return response.text().then(text => {
                    if(confirm(text)){
                        if(text.includes("đăng nhập")){
                            window.location.href = "login.jsp";
                            return;
                        }
                        window.location.reload();
                    }
                })
            })
            .then(data => window.location.href = data.url)
            .catch((error) => function (){
                console.log(error);
                if(confirm(error)){
                    window.location.reload()
                }
            });

    }
    document.getElementById('payment-btn').addEventListener('click', SubmitData)
</script>
</body>
</html>
