<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="com.busticketbooking.busticketbooking.Utils.ConvertUtil" %>
<%@ page import="com.busticketbooking.busticketbooking.models.Trip" %><%--
    Document   : index.jsp
    Created on : Sep 23, 2023, 12:13:14 AM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <title>DN BUS</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<style>
    body {
        margin: 0;
        padding: 0;
        font-weight: 500;
        font-family: 'Times New Roman', Times, serif;
    }

    .header {
        background-color: rgb(88, 140, 126);
        margin-bottom: 70px;
    }

    .sign-up, .log-out {
        float: right;
        margin-top: 10px;
        padding: 5px 0;
        border-radius: 15px;
        background-color: white;
    }

    .sign-up i {
        color: black;
        letter-spacing: 0.1em;
        font-weight: 500;
    }

    .sign-up i:hover {
        font-weight: bolder;
        text-decoration: underline;
    }

    .sign-up a {
        text-decoration: none;
    }

    .log-out i {
        color: black;
        letter-spacing: 0.1em;
        font-weight: 500;
    }

    .log-out i:hover {
        font-weight: bolder;
        text-decoration: underline;
    }

    .log-out a {
        text-align: end;
    }

    .icon-logout a {
        float: right;
        margin-top: 10px;
        padding: 5px 0;
        border-radius: 15px;
    }

    .logo {
        width: 150px;
        border-radius: 70%;
        -moz-border-radius: 70%;
        -webkit-border-radius: 70%;
    }

    .row {

    }

    .row div {
        display: flex;
        align-items: center;
        padding-right: 10px;
        justify-content: space-between;
    }

    .row a {
        text-decoration: none;
        color: #fff;
        align-items: center;
    }

    .col-sm-12 a {
        font-size: 20px;
    }

    .col-sm-12 a:hover {
        text-decoration: underline;
        font-weight: bolder;
    }

    .log-out {
        float: right;
    }

    .grid-container1 {
        width: 1000px;
        margin: auto;
    }

    .select {
        margin-top: 15px;
        margin-left: 25px;
    }

    .instructions {
        margin-right: 25px;
        margin-top: 15px;
        color: darkgreen;
    }

    .search {
        margin-bottom: 50px;
    }

    .thong-tin {
        display: flex;
        justify-content: space-between;
    }

    .gioi-thieu h1 {
        margin-top: 20px;
    }

    .search-content-items {
        background-color: rgb(88, 140, 126);
    }

    .search-content-item {
        background-color: rgb(88, 140, 126);
        display: flex;
        align-items: center;
        border-radius: 5px;
        position: relative;
    }

    .search-content-item input {
        position: absolute;
        width: 100%;
        height: 100%;
        border: none;
        outline: none;
        padding-left: 36px;
        border-radius: 5px;
    }

    .search-content-item i {
        position: absolute;
        margin-right: 12px;
        left: 12px;
        z-index: 1;
    }

    .search-content-item button {
        width: 130px;
    }

    .search-content-item button {
        height: 100%;
        width: 100%;
        color: #fff;
        background-color: rgb(88, 140, 126);
        border: none;
        cursor: pointer;
        border-radius: 5px;
    }

    .search-content-item button:hover {
        text-decoration: underline;
        font-weight: bolder;
    }

    .gioi-thieu {
        text-align: center;
    }

    .achievements .container {
        justify-content: space-between;
        display: flex;

    }

    .achievements {
        margin-top: 5rem;
    }

    .achievements figure {
        display: flex;
    }

    .achievements figcaption {
        margin-left: 1rem;
    }

    .achievements a {
        color: #000;
        text-decoration: none;
    }

    .achievements h3 {
        font-size: 24px;
        font-family: "Inter" !important;
        font-weight: 700;
    }

    .achievements p {
        font-size: 15px;
        font-family: "Inter" !important;
        margin-bottom: 50px;
    }

    .achievements li {
        margin-bottom: 1rem;
    }

    .gioi-thieu {
        margin-bottom: 40px;
    }

    .gioi-thieu2 {
        text-align: center;

    }

    .gioi-thieu2 h1 {
        margin-top: 100px;

    }

    .gioi-thieu2 p {
        margin-bottom: 50px;
    }

    .noibat {
        margin-bottom: 60px;
    }

    .noibat-1 {
        grid-area: noibat-1;
    }

    .noibat-2 {
        grid-area: noibat-2;
    }

    .noibat-3 {
        grid-area: noibat-3;
    }

    .noibat-4 {
        grid-area: noibat-4;
    }

    .grid-container {
        display: grid;
        grid-template-areas:
      'noibat-1 noibat-1 noibat-2 noibat-2 noibat-3 noibat-3 noibat-4 noibat-4';
        gap: 10px;
    }

    figcaption p {
        font-weight: bold;
    }

    .noibat img {
        width: 100%;
        height: 90%;
        object-fit: cover;
    }

    .grid-container {
        margin-top: 80px;
    }

    .noibat figure {
        height: 100%;
        display: block;
    }

    .contact img {
        width: 100%;
        height: 90%;
        object-fit: cover;
    }

    .contact figure {
        height: 100%;
        display: block;
    }

    .contact img {
        border-radius: 50%;
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
    }

    figcaption {
        color: #000;
    }

    /* footer */
    footer > p {
        text-align: center;
        background: #588C7E;
        height: 35px;
        line-height: 35px;
        font-size: 14px;
        color: #fff;
        text-transform: uppercase;
        font-weight: bold;
        margin-top: 10px;
    }

    .grid-container1 {
        display: flex;
        justify-content: space-between;
    }

    .register button {
        border: none;
        color: #fff;
        padding: 6px;
    }

    .gird-item1 a {
        color: black;
    }

    .register a {
        text-decoration: none;
        border-radius: 7rem;
        color: #fff;
        background-color: #EF5222;
        padding: 6px;
    }

    ul {
        list-style: none;
    }

    a {
        text-decoration: none;
    }

    footer {
        margin-bottom: none;
        margin-top: 50px;
    }

    .height {
        min-height: 400px;
    }

    #searchResults {
        background-color: rgb(88, 140, 126); /* Change this to your desired background color */
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin: 10px;
        font-size: 16px;
        color: #333; /* Text color */
    }
    hr{
        height: 2px;
        width: 100%;
        background-color: #ccc;
        border: none;
    }
    #suggestionBoxFrom {
        border: 1px solid #ccc;
        max-height: 150px;
        overflow-y: auto;
        display: none;
        background-color: white;
    }
    .suggestion {
        padding: 5px 10px;
        cursor: pointer;
    }
    .suggestion:hover {
        background-color: #eee;
    }
</style>
<body>
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-sm-10"></div>
            <div class="col-sm-2">
                <c:if test="${sessionScope.acc != null}">
                <div class="log-out"><a href="profile.jsp"><i
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

<div class="container d-flex flex-column ">
    <div class="container-1 container ">
        <!--Thanh tìm kiếm!-->
        <section class="border border-2 shadow rounded p-1 mb-3 bg-white">
            <form id="searchForm" method="get" action="searchTrip">
                <div class="search-content-items d-flex flex-column rounded p-2 ">
                    <div class="d-flex justify-content-around">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" placeholder="Tp HCM" value="${fromlocation}"
                                   name="fromlocation"
                                   id="fromlocation" required/>
                            <label for="fromlocation">Điểm đi</label>
                            <div id="suggestionBoxfrom" style="position: absolute; max-height: 150px; overflow-y: auto; width: 100%; border: 1px solid #ccc;color: black; background-color: white;"></div>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" placeholder="Hà nội" class="form-control" value="${tolocation}"
                                   name="tolocation"
                                   id="tolocation" required/>
                            <label for="tolocation">Điểm đến</label>
                            <div id="suggestionBoxto" style="position: absolute; max-height: 150px; overflow-y: auto; width: 100%; border: 1px solid #ccc;color: black; background-color: white;"></div>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" placeholder="" name="datego"
                                   value="<%=(Date) request.getAttribute("datego")%>"
                                   id="datego"/>
                            <label for="datego">Ngày xuất hành</label>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center">
                        <input type="submit" value="Tìm kiếm" class="btn px-4 btn-light">
                    </div>
                </div>
            </form>
        </section>
        <!--Thanh tìm kiếm!-->
        <div id="searchResults"></div>
    </div>

    <div class="row mt-3">
        <div class="col-4"></div>
        <div class="col-8">
            <div class="container">
                <section id="searchResult" class="mt-3 w-100">
                    <div class="d-flex flex-column align-items-start fs-5">
                        <h4>${fromlocation} - ${tolocation} (${trips.size()})</h4>
                        <%for (Trip trip : (List<Trip>) request.getAttribute("trips")) {%>
                        <div class="d-flex p-3 flex-column w-100 align-items-center border-2 rounded shadow mb-3">
                            <div class="d-flex justify-content-between align-items-center mb-2 w-100">
                                <span class="me-2"><%=ConvertUtil.formatTime(trip.getTime())%></span>
                                <div class="d-flex w-100 align-items-center justify-content-between">
                                    <img src="https://futabus.vn/images/icons/pickup.svg" alt="pickup">
                                    <span class="flex-1 border-2 w-25 bg-primary" style="height: 2px;"></span>
                                    <span class="text-center"><%=ConvertUtil.floatToHourAndMinutes(trip.getRoute().getDuration())%></span>
                                    <span class="flex-1 border-2 w-25 bg-success" style="height: 2px;"></span>
                                    <img src="https://futabus.vn/images/icons/station.svg" alt="station">
                                </div>
                                <span class="ms-2"><%=ConvertUtil.formatTime(ConvertUtil.calculateEndTime(trip.getTime(), trip.getRoute().getDuration()))%></span>
                            </div>
                            <div class="d-flex justify-content-between align-items-center w-100">
                                <p class="my-0"><%=trip.getRoute().getOrigin()%>
                                </p>
                                <p class="my-0"><%=trip.getRoute().getDestination()%>
                                </p>
                            </div>
                            <hr>
                            <div class="d-flex  justify-content-between mb-2 w-100">
                                <div class="d-flex align-items-center justify-content-center">
                                    <p class="my-0 text-warning"><%=ConvertUtil.formatToMoney(trip.getRoute().getFare())%>
                                    </p>
                                    <p class="my-0 text-danger mx-2">&#8226;</p>
                                    <p class="mx-2 my-0"><%=trip.getRoute().getVehicle().getVehicleType()%>
                                    </p>
                                    <p class="my-0 text-danger mx-2">&#8226;</p>
                                    <p class="text-warning m-0"><%=trip.getSeatingInformation()%> chỗ trống</p>
                                </div>
                                <a href="booking-ticket?tripId=<%=trip.getTripId()%>" class="btn btn-success px-3">Chọn
                                    chuyến</a>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </section>
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
<script>
    $(document).ready(function() {
        
        $('#fromlocation').on('input', function() {
            let query = $(this).val();
            if (query) {
                $.ajax({
                    type: 'GET',
                    url: '<%=request.getContextPath()%>/findlocation',
                    data: { fromLocation: query },
                    success: function(data) {
                        console.log(data);
                        populateSuggestions(data, 'from');
                    },
                    error: function(error) {
                        console.error("Error fetching data:", error);
                    }
                });
            } else {
                $('#suggestionBoxFrom').hide();
            }
        });

        $('#tolocation').on('input', function() {
            let query = $(this).val();
            if (query) {
                $.ajax({
                    type: 'GET',
                    url: '<%=request.getContextPath()%>/findlocation',
                    data: { toLocation: query },
                    success: function(data) {
                        console.log(data);
                        populateSuggestions(data, 'to');
                    },
                    error: function(error) {
                        console.error("Error fetching data:", error);
                    }
                });
            } else {
                $('#suggestionBoxTo').hide();
            }
        });
        // If you click outside the input and suggestion box, hide suggestions
        $(document).on('click', function(event) {
            if (!$(event.target).closest('#fromlocation').length && !$(event.target).closest('#suggestionBoxfrom').length) {
                $('#suggestionBoxfrom').hide();
            }
        });
        $(document).on('click', function(event) {
            if (!$(event.target).closest('#tolocation').length && !$(event.target).closest('#suggestionBoxto').length) {
                $('#suggestionBoxto').hide();
            }
        });
    });

    function populateSuggestions(locations, choice) {
        let $suggestionBox = $('#suggestionBox' +choice);
        $suggestionBox.empty().show();

        locations.forEach(locationObj => {
            let $suggestion = $('<div class="suggestion"></div>').text(locationObj);
            $suggestion.on('click', function() {
                $('#'+choice+'location').val(locationObj);
                $suggestionBox.hide();
            });
            $suggestionBox.append($suggestion);
        });
    }
    function validateForm() {
        // Validate fromLocation and toLocation
        var fromLocation = document.getElementById("fromlocation").value;
        var toLocation = document.getElementById("tolocation").value;

        if (!fromLocation || !toLocation) {
            alert("Điểm đi và điểm đến không được trống.");
            return false;
        }

        // Validate dateGo is not smaller than today
        var dateGo = document.getElementById("datego").value;
        var today = new Date().toISOString().split('T')[0];
        if (dateGo < today) {
            alert("Ngày xuất hành không được nhỏ hơn ngày hiện tại.");
            return false;
        }

        // Validate quantity is greater than 0 and less than 5
        var quantity = document.getElementById("quantity").value;
        if (quantity <= 0 || quantity >= 5) {
            alert("Số vé phải lớn hơn 0 và nhỏ hơn 5.");
            return false;
        }

        // If all validations pass
        return true;
    }

</script>
</body>
</html>

</body>
</html>
