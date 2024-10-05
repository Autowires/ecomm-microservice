<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f9f9f9;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Ensure footer sticks to the bottom */
        }

        .header {
            background-color: rgb(8, 170, 134);
            color: white;
            padding: 15px;
            display: flex;
            align-items: center;
        }

        .header img {
            max-height: 50px; /* Adjust the height of the logo */
            margin-right: 15px; /* Space between logo and name */
        }

        .card {
            transition: transform 0.2s;
        }

        .card:hover {
            transform: scale(1.05);
        }

        footer {
            background-color: rgb(8, 170, 134);
            color: white;
            padding: 20px;
            margin-top: auto; /* Ensure footer stays at the bottom */
        }

        .content {
            flex: 1; /* Allow the content to grow */
        }
    </style>
</head>

<body>
  
<jsp:include page="header.jsp" />

<div class="content">
    <main class="container my-5">
        <div class="row">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">View Orders</h5>
                        <p class="card-text">Access and manage your orders.</p>
                        <button class="btn btn-primary" onclick="location.href='/'">View Orders</button>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Manage Inventory</h5>
                        <p class="card-text">Quick access to your inventory management system.</p>
                        <button class="btn btn-primary" onclick="location.href='/retailer/inventory'">Go to Inventory</button>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Register a Product</h5>
                        <p class="card-text">Easily add new products to your catalog.</p>
                        <button class="btn btn-primary" onclick="location.href='/retailer/inventory/add-product'">Register Product</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<footer>
    <div class="text-center">
        <p>&copy; 2023 Your Retailer Name. All rights reserved.</p>
    </div>
</footer>

<!-- Add this CDN for FontAwesome icons -->
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function logout() {
        alert("Logging out...");
        // Redirect to login page or home page after logout
        window.location.href = '/login'; // Update with your actual login path
    }
</script>

</body>
</html>
