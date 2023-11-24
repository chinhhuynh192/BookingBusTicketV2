<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/8/2023
  Time: 5:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="themify-icons/themify-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="assets/css/styles.min.css"/>
    <title>Booking Manage</title>
</head>
<body>
<!--  Body Wrapper -->
<div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
     data-sidebar-position="fixed data-header-position=fixed">
<!-- Sidebar Start -->
<aside class="left-sidebar">
    <!-- Sidebar scroll-->
    <div>
        <div class="brand-logo d-flex align-items-center justify-content-between">
            <a href="index.jsp" class="text-nowrap logo-img">
                <img src="logo.png" width="100%" alt=""/>
            </a>
            <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                <i class="ti ti-x fs-8"></i>
            </div>
        </div>
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
            <ul id="sidebarnav">
                <li class="nav-small-cap">
                    <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                    <span class="hide-menu">Home</span>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="dashboard.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-layout-dashboard"></i>
                </span>
                        <span class="hide-menu">Dashboard</span>
                    </a>
                </li>
                <li class="nav-small-cap">
                    <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                    <span class="hide-menu">UI COMPONENTS</span>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="route" aria-expanded="false">
                <span>
                  <i class="ti ti-article"></i>
                </span>
                        <span class="hide-menu">Route</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="trip" aria-expanded="false">
                <span>
                  <i class="ti ti-alert-circle"></i>
                </span>
                        <span class="hide-menu">Trip</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="vehicle" aria-expanded="false">
                <span>
                  <i class="ti ti-cards"></i>
                </span>
                        <span class="hide-menu">Vehicle</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="booking-manage" aria-expanded="false">
                <span>
                  <i class="ti ti-cards"></i>
                </span>
                        <span class="hide-menu">Booking</span>
                    </a>
                </li>
            </ul>

        </nav>
        <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
</aside>
<!--  Sidebar End -->
<!--  Main wrapper -->
<div class="body-wrapper">
    <!--  Header Start -->
    <header class="app-header">
        <nav class="navbar navbar-expand-lg navbar-light">
            <ul class="navbar-nav">
                <li class="nav-item d-block d-xl-none">
                    <a class="nav-link sidebartoggler nav-icon-hover" id="headerCollapse" href="javascript:void(0)">
                        <i class="ti ti-menu-2"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-icon-hover" href="javascript:void(0)">
                        <i class="ti ti-bell-ringing"></i>
                        <div class="notification bg-primary rounded-circle"></div>
                    </a>
                </li>
            </ul>
            <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
                <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
                    <li class="nav-item dropdown">
                        <a class="nav-link nav-icon-hover" href="javascript:void(0)" id="drop2"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <img src="assets/images/profile/user-1.jpg" alt="" width="35" height="35"
                                 class="rounded-circle">
                        </a>
                        <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                            <div class="message-body">
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-user fs-6"></i>
                                    <p class="mb-0 fs-3">My Profile</p>
                                </a>
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-mail fs-6"></i>
                                    <p class="mb-0 fs-3">Report</p>
                                </a>
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-list-check fs-6"></i>
                                    <p class="mb-0 fs-3">Check</p>
                                </a>
                                <a href="index.jsp" class="btn btn-outline-primary mx-3 mt-2 d-block">Trang chủ</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!--  Header End -->
    <div class="container-fluid">
       <div class="d-flex flex-column justify-content-center align-items-center">
            <h4>Booking list</h4>
            <table class="table" id="booking-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th colspan="2">Chuyến đi</th>
                        <th colspan="2">Thời gian</th>
                        <th colspan="6">Thông tin khách hàng(Tên, Số điện thoại, Email)</th>
                        <th colspan="2">Ngày đặt</th>
                        <th>Số ghế đặt</th>
                        <th>Tổng giá</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <jsp:useBean id="Helper" class="com.busticketbooking.busticketbooking.Utils.ConvertUtil"></jsp:useBean>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td>${booking.bookingId}</td>
                            <td colspan="2">${booking.trip.route.origin} -  ${booking.trip.route.destination}</td>
                            <td colspan="2">${Helper.formatTime(booking.trip.time)} ${Helper.formatDate(booking.trip.date)}</td>
                            <td colspan="6">${booking.user.name} || ${booking.user.phone} || ${booking.user.email}</td>
                            <td colspan="2">${booking.dateBooking}</td>
                            <td>${booking.seatNumber}</td>
                            <td>${Helper.calculateAmount(booking.price, booking.discount)}</td>
                            <td>${booking.bookingStatus}</td>
                            <td>
                                <c:if test="${booking.bookingStatus eq 'WaitingPayment'}">
                                    <button type="button" class="btn btn-sm btn-success accept-btn" data-id-booking="${booking.bookingId}" onclick="submitBooking(this)">Accept</button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
       </div>
    </div>
</div>
</div>
<script src="assets/libs/jquery/dist/jquery.min.js"></script>
<script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/sidebarmenu.js"></script>
<script src="assets/js/app.min.js"></script>
<script src="assets/libs/apexcharts/dist/apexcharts.min.js"></script>
<script src="assets/libs/simplebar/dist/simplebar.js"></script>
<script src="assets/js/dashboard.js"></script>
<script>
    var ws = new WebSocket(getWebSocketServer());
    ws.onmessage = function(event) {
        var booking = JSON.parse(event.data);
        // Assuming you have a function that can add a booking row to the table
        addBookingToTable(booking);
    };

    function getWebSocketServer() {
        var protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
        var host = window.location.host;
        var contextPath = '<%=request.getContextPath()%>'; // adjust if necessary
        var endpoint = '/admin/bookings';

        return protocol + host + contextPath + endpoint;

    }

    function addBookingToTable(booking) {
        console.log(booking);
        // Construct the new table row as a string
        let newRow = `
    <tr>
      <td>\${booking.bookingId}</td>
      <td colspan="2">\${booking.trip.route.origin} - \${booking.trip.route.destination}</td>
      <td colspan="2">` + formatTripDateTime(booking.trip.date, booking.trip.time) + `</td>
      <td colspan="6">\${booking.user.name} || \${booking.user.phone} || \${booking.user.email}</td>
      <td colspan="2">` + formatDateBooking(booking.dateBooking) + `</td>
      <td>\${booking.seatNumber}</td>
      <td>` + formatPrice(booking.price, booking.discount) + `</td>
      <td>\${booking.bookingStatus}</td>
      <td>
        \${booking.bookingStatus === 'WaitingPayment' ? "<button type='button' class='btn btn-sm btn-success accept-btn' data-id-booking='"+booking.bookingId+"' onclick='submitBooking(this)'>Accept</button>" : ""}
      </td>
    </tr>`;

        // Append the new row to the table body
        let tableBody = document.getElementById('booking-table').getElementsByTagName('tbody')[0];
        tableBody.insertAdjacentHTML('afterbegin', newRow);
    }

    // Helper function to add leading zero
    function addLeadingZero(number) {
        return number < 10 ? '0' + number : number;
    }

    // Function to format booking date
    function formatDateBooking(dateString) {
        let date = new Date(dateString);
        return `\${date.getFullYear()}-\${addLeadingZero(date.getMonth() + 1)}-\${addLeadingZero(date.getDate())}`;
    }

    // Function to format trip date and time
    function formatTripDateTime(dateString, timeString) {
        // Ensure time string matches the expected format
        const timeRegex = /(\d+):(\d+):(\d+) (\w+)/;
        const timeParts = timeRegex.exec(timeString);

        if (!timeParts) {
            console.error('Time string does not match the expected format');
            return '';
        }

        // Extract parts of the time
        let [ , hours, minutes, , amPm ] = timeParts;
        hours = parseInt(hours, 10);

        // Convert 12-hour time to 24-hour time
        if (amPm === 'PM' && hours < 12) {
            hours += 12;
        } else if (amPm === 'AM' && hours === 12) {
            hours = 0;
        }

        // Pad the hours with zeros if necessary
        hours = hours.toString().padStart(2, '0');

        // Parse the date part
        const dateObject = new Date(dateString);
        if (isNaN(dateObject)) {
            console.error('Date string is invalid');
            return '';
        }

        const year = dateObject.getFullYear();
        const month = (dateObject.getMonth() + 1).toString().padStart(2, '0');
        const day = dateObject.getDate().toString().padStart(2, '0');

        // Concatenate the formatted date and time
        const formattedDateTime = `\${hours}:\${minutes} \${day}-\${month}-\${year}`;

        return formattedDateTime;
    }

    // Function to format price
    function formatPrice(price, discount) {
        var amount =  price * (1 - discount);
        return amount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, '.') + 'đ';
    }

    function submitBooking(button) {
        var bookingId = button.getAttribute('data-id-booking');

        console.log(bookingId)
        // Create a form element
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = '<%=request.getContextPath()%>' + '/booking-manage'; // Replace with your endpoint

        // Create input element for bookingId
        var hiddenField = document.createElement('input');
        hiddenField.type = 'hidden';
        hiddenField.name = 'bookingId';
        hiddenField.value = bookingId;

        // Append the input to the form
        form.appendChild(hiddenField);

        // Append the form to the body
        document.body.appendChild(form);

        // Submit the form
        form.submit();
    }

</script>
</body>
</html>
