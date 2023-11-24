<%@page import="java.sql.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.busticketbooking.busticketbooking.models.Account"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Ticket History</title>
    <style>
        body {
            background-color: rgb(88, 140, 126);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        .ticket-history {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            text-align: center;
        }
        
        .ticket-history h1 {
            margin-bottom: 20px;
        }
        
        .ticket-details {
            text-align: left;
            margin-bottom: 10px;
            border: 1px solid #dddddd;
            padding: 10px;
            border-radius: 5px;
        }
        
        .ticket-details strong {
            display: inline-block;
            width: 120px;
        }
    </style>
</head>
<body>
    <div class="ticket-history">
        <h1>My Ticket History</h1>
        <%
             Account acc = (Account) session.getAttribute("acc");
            int userId = acc.getId();
            
            String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=BusTicketBooking;trustServerCertificate=true;";
            String username = "sa";
            String password = "123";

            try (Connection connection = DriverManager.getConnection(connectionString, username, password)) {
                String query = "SELECT * FROM Booking WHERE user_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, userId);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int bookingId = resultSet.getInt("booking_id");
                            int tripId = resultSet.getInt("trip_id");
                            Date dateBooking = resultSet.getDate("date_booking");
                            String seatNumber = resultSet.getString("seat_number");
                            float price = resultSet.getFloat("price");
                            float discount = resultSet.getFloat("discount");
                            String bookingStatus = resultSet.getString("booking_status");

                            %>
                            <div class="ticket-details">
                                <strong>Booking ID:</strong> <%= bookingId %><br>
                                <strong>Trip ID:</strong> <%= tripId %><br>
                                <strong>Date Booking:</strong> <%= dateBooking %><br>
                                <strong>Seat Number:</strong> <%= seatNumber %><br>
                                <strong>Price:</strong> <%= price %><br>
                                <strong>Discount:</strong> <%= discount %><br>
                                <strong>Booking Status:</strong> <%= bookingStatus %><br>
                            </div>
                            <%
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </div>
</body>
</html>