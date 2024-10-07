<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Console</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        body, html {
            height: 100%;
            margin: 0;
        }

        .main-container {
            min-height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .content-container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            text-align: center;
            padding: 50px;
            border-radius: 8px;
            background-color: #f9f9f9;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 26px;
            color: #333;
            margin-bottom: 15px;
        }

        p {
            font-size: 16px;
            color: #666;
            margin-bottom: 30px;
        }

        .google-btn {
            background-color: #4285F4;
            color: white;
            padding: 12px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .google-btn:hover {
            background-color: #357ae8;
        }

        footer {
            text-align: center;
            padding: 20px 0;
            background-color: #f8f9fa;
            color: #777;
        }

        footer p {
            margin: 0;
        }
    </style>
</head>
<body>

<div class="main-container">
    <!-- Main Content -->
    <div class="content-container">
        <div class="container">
            <h1>Welcome to Admin Console</h1>
            <p>Please sign in to access the admin dashboard</p>

            <!-- Google Sign-In Button -->
            <a class="google-btn" href="${pageContext.request.contextPath}/oauth2/authorization/google">
                Continue with Google
            </a>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>© 2024 Zip stores. All rights reserved.</p>
    </footer>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
