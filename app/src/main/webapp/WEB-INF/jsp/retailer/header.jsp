<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- FontAwesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
    <style>
        /* Custom styles */
        header {
            background-color: #f8f9fa;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }
        
        .navbar-light .navbar-toggler {
            border-color: #ddd;
        }

        .navbar-light .navbar-toggler:hover {
            background-color: #f1f1f1;
        }

        .nav-link {
            font-weight: 500;
            color: #5a5a5a;
        }

        .nav-link:hover {
            color: #007bff;
        }

        .header-logo img {
            height: 60px;
        }

        .user-menu .nav-link p {
            font-size: 0.9rem;
            margin-left: 0.5rem;
        }

        .bg-white {
            background-color: #fff !important;
        }

        .jumbotron-container {
            background-color: #ffffff;
            padding: 20px;
            border-bottom: 1px solid #e9ecef;
        }

        .jumbotron-container a img {
            max-height: 60px;
        }

        .user-menu i {
            font-size: 1.2rem;
        }
    </style>
</head>
<body>
    <!--Main Navigation-->
    <header>
        <!-- Jumbotron -->
        <div class="p-3 text-center bg-white border-bottom jumbotron-container">
            <div class="container">
                <div class="row align-items-center">
                    <!-- Left elements: Logo -->
                    <div class="col-lg-2 col-sm-4 col-4">
                        <a href="#" class="header-logo"> 
                            <img src="/img/zip.png" alt="Logo">
                        </a>
                    </div>
                    <!-- Center elements: User Info and Links -->
                    <div class="col-lg-5 ms-auto user-menu">
                        <div class="d-flex justify-content-end">
                            <!-- Check if user is logged in using JSTL -->
                            <a href="#" class="nav-link d-flex align-items-center me-3">
                               
                                <p>Welcome, ${sessionScope.username}</p>
                            </a>
                            <a href="/retailerDash" class="nav-link d-flex align-items-center me-3">
                                
                                <p>Home</p>
                            </a>
                            <a href="/retailer/inventory" class="nav-link d-flex align-items-center me-3">
                                
                                <p>Inventory</p>
                            </a>
                            <a href="/retailer/orders" class="nav-link d-flex align-items-center me-3">
                            
                                <p>Orders</p>
                            </a>
                            <a href="/logout" class="nav-link d-flex align-items-center">
                                
                                <p>Logout</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Jumbotron End -->

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-white">
            <div class="container">
                <!-- Toggle button -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarLeftAlignExample"
                    aria-controls="navbarLeftAlignExample" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarLeftAlignExample">
                    <!-- Add additional navbar items here if needed -->
                </div>
            </div>
        </nav>
        <!-- Navbar End -->
    </header>

    <!-- Bootstrap JS (Bundle with Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
