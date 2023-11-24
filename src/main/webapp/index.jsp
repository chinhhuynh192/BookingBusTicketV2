<%-- 
    Document   : index.jsp
    Created on : Sep 23, 2023, 12:13:14 AM
    Author     : Admin
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
        <script src="node_modules/jquery/dist/jquery.slim.min.js"></script>
        <script src="node_modules/popper.js/dist/umd/popper.min.js"></script>
        <script src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
        <link rel="stylesheet" href="/BookingTicket//themify-icons/themify-icons.css">
         <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link rel="icon" href="logo.png">
        
        <link rel="stylesheet" href="index.css">
        <title>DN BUS</title>
    </head>    
    <style>
        body{
    margin: 0;
    padding: 0;
    font-weight: 500;
    font-family :'Times New Roman', Times, serif; 
}
</style>
<body>
    <div class="header">
            <div class="container">
                <div class="row">
                    <div class="col-sm-10"></div>
                    <div class="col-sm-2">
                        <c:if test="${sessionScope.acc != null}">
                            <div class="log-out"><a href="<%=request.getContextPath()%>/profile"><i class="fas fa-user fa-sm">${sessionScope.acc.name}</i></a></div>
                            <div class="icon-logout"><a href="Logout"><i class="fas fa-sign-out-alt fa-lg"></i></a>
                        </c:if>
                        <c:if test="${sessionScope.acc == null}">
                    <div class="sign-up"><a href="login.jsp"><i class="fas fa-user fa-sm">Đăng kí/Đăng nhập</i></a></div>
                        </c:if>
                    </div>
                </div>
                <div class="row">
            <div class="col-sm-12">
                <a href="index.jsp"><img src="image/logo.png" class="logo"></a>
                <div class="home"><a href="">Trang chủ  </a></div>
                <div class="search-ticket"><a href="ticket.jsp">Tra cứu vé</a></div>
                <div class="new"><a href="">Tin tức</a></div>
               <c:if test="${sessionScope.acc.role == 'customer'}">
                <div class="contact"><a href="myticket.jsp">Vé của tôi</a></div>
                </c:if>
                <c:if test="${sessionScope.acc.role == 'admin'}">
               <div class="about-us"><a href="dashboard.jsp">Quản lí</a></div>
                </c:if>
            </div>
        </div>
        </div>
        </div>
    </div>
    
    
       <div class="container ">
        <div class="container-1 container ">
            <!--Thanh tìm kiếm!-->
            <section class="border border-2 shadow rounded p-1 mb-3 bg-white">
                <form id="searchForm" method="get" action="searchTrip">
                    <div class="search-content-items d-flex flex-column rounded p-4 justify-content-center align-items-center ">
                        <h4 class="fs-3 fw-bolder text-white my-3 ">Thanh tìm kiếm</h4>
                        <div class="d-flex justify-content-around w-100">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" placeholder="Tp HCM" name="fromlocation"
                                       id="fromlocation" required/>
                                <label for="fromlocation">Điểm đi</label>
                                <div id="suggestionBoxfrom" style="position: absolute; max-height: 150px; overflow-y: auto; width: 100%; border: 1px solid #ccc;color: black; background-color: white;"></div>

                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" placeholder="Hà nội" class="form-control" name="tolocation"
                                       id="tolocation" required/>
                                <label for="tolocation">Điểm đến</label>
                                <div id="suggestionBoxto" style="position: absolute; max-height: 150px; overflow-y: auto; width: 100%; border: 1px solid #ccc;color: black; background-color: white;"></div>

                            </div>
                            <div class="form-floating mb-3">
                                <input type="date" class="form-control" placeholder="" name="datego"
                                       id="datego" required>
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
    </div>

      <div class="gioi-thieu">
        <h1>DN BUS LINES - CHẤT LƯỢNG LÀ CỐT LÕI</h1>
        <p>Được khách hàng tin tưởng và lựa chọn
        </p>
      </div>
          <section class="achievements">
            <div class="container">
          <div>
            <ul>
              <li>
                <a href="#">
                  <figure>
                    <img width="20%"
                      src="image/customers.svg"
                      alt=""
                    />
                    <figcaption>
                      <h3>
                        Hơn 100K lượt khách mỗi năm 
                      </h3>
                      <span>
                        Làm hài lòng số đông khách hàng
                      </span>
                    </figcaption>
                  </figure>
                </a>
              </li>
              <li>
                <a href="#">
                  <figure>
                    <img width="20%"
                      src="image/location.svg"
                      alt=""
                    />
                    <figcaption>
                      <h3>
                        Nhiều văn phòng trải dài trên khắp cả nước 
                      </h3>
                      <span>
                        Bến xe, trạm trung chuyển,... trên toàn hệ thống
                      </span>
                    </figcaption>
                  </figure>
                </a>
              
                <a href="#">
                  <figure>
                    <img width="20%"
                      src="image/like.svg"
                      alt=""
                    />
                    <figcaption>
                      <h3>
                        Xe hiện đại nhất thị trường hiện nay
                      </h3>
                      <span>
                        Với những tiện ích khác biệt với thị trường
                      </span>
                    </figcaption>
                  </figure>
                </a>
              </li>
            </ul>
          </div>
          <div>
            <img width="100%" height="100%" src="image/background.svg" alt="">
          </div>
      </section> 
    <div class="gioi-thieu">
      <h1>Tin tức mới</h1>
      
      
  
    </div>
    <section class="noibat">
        <div class="height">
      <div class="grid-container container">
        <div class="noibat-1">
          <a href="">
          <figure>
            <div class="anh1">
              
            </div>
            <img
              src="image/uudai.jpg"
              alt=""
            />
            <figcaption>
              <p>Đặt xe ĐN với nhiều ưu đãi</p>
              <time> 31/05/2023</time>
            </figcaption>
          </figure>
        </a>
        </div>
        <div class="noibat-2">
          <a href="">
            <figure>
                <img
                src="image/uu dai 2.jpg"
                alt=""
              />
              <figcaption>
                <p>Tiết kiệm hơn khi thanh toán qua momo</p>
                <time> 31/05/2023</time>
              </figcaption>
            </figure>
          </a>
        </div>
        <div class="noibat-3">
          <a href="">
            <figure>
              <img src="image/uu dai 3.jpg" alt="" />
              <figcaption>
                <p>Tiết kiệm hơn khi thanh toán qua VN pay</p>
                <time> 31/05/2023</time>
              </figcaption>
            </figure>
          </a>
        </div>
        <div class="noibat-4">
          <a href="">
            <figure>
              <img src="image/tram dung chan.jpg" alt="" />
              <figcaption>
                <p>Điểm dừng chân mới </p>
                <time> 31/05/2023</time>
              </figcaption>
            </figure>
          </a>
        </div>
        </div>
       </div>
    </section>
    <div class="gioi-thieu2">
        <div class="height">
      <h1>NHỮNG CHƯƠNG TRÌNH Ý NGHĨA CHO CỘNG ĐỒNG</h1>
      
      <p>Các chương trình từ thiện giúp đỡ các hoàn cảnh khó khăn luôn diễn ra thường xuyên</p>
      <section class="contact">
        <div class="grid-container container">
          <div class="img1">
            <a href="">
            <figure> 
              <img
                src="image/tuthien1.jpeg"
                alt=""
              />
              <figcaption>xe hợp đồng
              </figcaption>
            </figure>
          </a>
          </div>
          <div class="img2">
            <a href="">
              <figure>
                <img
                  src="image/tuthien2.jpg"
                  alt=""
                />
                <figcaption>
                  Mua vé xe ĐN
                </figcaption>
              </figure>
            </a>
          </div>
          <div class="img3">
            <a href="">
              <figure>
                <img src="image/tuthien3" alt="" />
                <figcaption>
                 Giao hàng
                </figcaption>
              </figure>
            </a>
          </div>
          <div class="img4">
            <a href="">
              <figure>
                <img src="image/tuthien4.jpg    " alt="" />
                <figcaption>
                  Xe buýt
                </figcaption>
              </figure>
            </a>
          </div>
          </div>
      </section>
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
                <input placeholder="Nhập địa chỉ email" />
                <div class="register"><button><a href="http://127.0.0.1:5500/BookingTicket/html/index.jsp">Đăng ký</a></button></div>
                
              </form>
            </li>
          </ul>
        </div>
      </div>
    </footer>
              <div class="chatbox-wrapper">
		<div class="chatbox-toggle">
			<i class='bx bx-message-dots'></i>
		</div>
		<div class="chatbox-message-wrapper">
			<div class="chatbox-message-header">
				<div class="chatbox-message-profile">
					<img src="https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8bWFufGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60" alt="" class="chatbox-message-image">
					<div>
						<h4 class="chatbox-message-name">Chăm sóc khách hàng</h4>
						<p class="chatbox-message-status">DN BUS</p>
					</div>
				</div>
				<div class="chatbox-message-dropdown">
					<i class='bx bx-dots-vertical-rounded chatbox-message-dropdown-toggle'></i>
					<ul class="chatbox-message-dropdown-menu">
						<li>
							<a href="#">Search</a>
						</li>
						<li>
							<a href="#">Report</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="chatbox-message-content">
				<h4 class="chatbox-message-no-message">You don't have message yet!</h4>

			</div>
			<div class="chatbox-message-bottom">
				<form action="#" class="chatbox-message-form">
					<textarea rows="1" placeholder="Type message..." class="chatbox-message-input"></textarea>
					<button type="submit" class="chatbox-message-submit"><i class='bx bx-send' ></i></button>
				</form>
			</div>
		</div>
	</div>
              <script src="index.js"></script>
</body>
</html>

    </body>
</html>