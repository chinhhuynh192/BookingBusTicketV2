<%@page import="com.busticketbooking.busticketbooking.models.Route"%>
<%@page import="com.busticketbooking.busticketbooking.models.Vehicle"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Routes</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        
        #container {
            width: 90%;
            margin: 0 auto;
        }
        
        h1 {
            text-align: center;
            margin-top: 30px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        th, td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }
        
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        
        .btn-edit, .btn-delete {
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .btn-edit {
            background-color: #4CAF50;
            color: white;
            margin-right: 5px;
        }
        
        .btn-delete {
            background-color: #f44336;
            color: white;
        }
        
        .btn-add {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #2196F3;
            color: white;
            margin-top: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div id="container">
        <h1>List of Vehicle</h1>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Image</th>
                    <th>License</th>
                    <th>Capacity</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Vehicle> listR = (List<Vehicle>) request.getAttribute("listR");
                    for (Vehicle vehicle : listR) {
                %>
                <tr>
                    <td><%= vehicle.getVehicleId() %></td>
                    <td><%= vehicle.getVehicleType() %></td>
                    <td><%= vehicle.getImageVehicle() %></td>
                    <td><%= vehicle.getLicensePlate() %></td>
                    <td><%= vehicle.getCapacity() %></td>
                    <td><%= vehicle.getVehicleStatus() %></td>
                    <td>
                        <!-- Edit Button -->
                        <button class="btn-edit" onclick="editRoute(<%= vehicle.getVehicleId()%>)"  >✏️ Edit</button>
                        <!-- Delete Button -->
                        <button class="btn-delete" onclick="deleteRoute(<%= vehicle.getVehicleId()%>)">❌ Delete</button>
                    </td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
        
        <!-- Add Vehicle Button -->
        <button class="btn-add" onclick="addVehicle()">➕ Add Vehicle</button>
    </div>

    <!-- JavaScript code for CRUD operations -->
    <script>
        // Function to edit a vehicle
        function editVehicle(vehicleId) {
            // TODO: Implement the edit functionality
        }
        
        // Function to delete a vehicle 
        function deleteRoute(routeId) {
            // TODO: Implement the delete functionality
        }
        
        // Function to add a new route
        function addRoute() {
            // TODO: Implement the add functionality
        }
    </script>
</body>
</html>