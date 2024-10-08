<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Dashboard</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f9f9f9;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            font-family: Arial, sans-serif;
        }

        .header {
            background-color: #08aa86;
            color: white;
            padding: 15px;
            display: flex;
            align-items: center;
        }

        .header img {
            max-height: 50px;
            margin-right: 15px;
        }

        .card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: none;
            border-radius: 10px;
        }

        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .card-body {
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
        }

        .card-title {
            font-size: 1.5rem;
            margin-bottom: 15px;
            color: #08aa86;
            font-weight: 600;
        }

        .card-text {
            margin-bottom: 20px;
        }

        .btn-primary {
            background-color: #08aa86;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #067d62;
        }

        .content {
            flex: 1;
            margin-top: 50px;
        }

        footer {
            background-color: #08aa86;
            color: white;
            padding: 20px;
            margin-top: auto;
        }

        footer p {
            margin: 0;
        }

        /* Additional spacing */
        .row {
            row-gap: 30px; /* Vertical gap between rows */
        }
    </style>
</head>

<body>
  
    <jsp:include page="header.jsp" />

    <div class="content">
        <main class="container my-5">
            <h2 class="text-center mb-5" style="color: #08aa86;">Retailer Dashboard</h2>
            <div class="row">
                <!-- Card 1: View Orders -->
                <div class="col-md-6 col-lg-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body text-center">
                            <h5 class="card-title">View Orders</h5>
                            <p class="card-text">Access and manage your orders.</p>
                            <button class="btn btn-primary" onclick="location.href='/retailer/orders'">View Orders</button>
                        </div>
                    </div>
                </div>

                <!-- Card 2: Manage Inventory -->
                <div class="col-md-6 col-lg-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body text-center">
                            <h5 class="card-title">Manage Inventory</h5>
                            <p class="card-text">Quick access to your inventory management system.</p>
                            <button class="btn btn-primary" onclick="location.href='/retailer/inventory'">Go to Inventory</button>
                        </div>
                    </div>
                </div>

                <!-- Add more cards if needed -->
            </div>
        </main>
    </div>

    <footer>
        <div class="text-center">
            <p>&copy; 2023 Zip stores. All rights reserved.</p>
        </div>
    </footer>

    <!-- FontAwesome icons -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.min.js"></script>

</body>

</html>
