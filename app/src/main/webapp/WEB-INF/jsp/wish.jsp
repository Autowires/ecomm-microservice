<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wishlist</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f0f2f5;
        }
        .wishlist-card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .wishlist-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }
        .wishlist-img {
            border-radius: 10px 10px 0 0;
            height: 200px;
            object-fit: cover;
        }
        .wishlist-info {
            padding: 20px;
            background-color: #fff;
            border-radius: 0 0 10px 10px;
        }
        .wishlist-title {
            font-weight: bold;
            font-size: 1.25rem;
        }
        .wishlist-price {
            color: #28a745;
            font-size: 1.1rem;
        }
    </style>
</head>
<body>

<div class="container my-5">
    <div class="row">
        <c:forEach var="item" items="${wishlist}">
            <div class="col-md-4 mb-4">
                <div class="card wishlist-card">
                    <img src="path_to_image/${item.image}" class="card-img-top wishlist-img" alt="Product Image">
                    <div class="wishlist-info">
                        <h5 class="wishlist-title">${item.title}</h5>
                        <p class="wishlist-price">$${item.price}</p>
                        <p class="wishlist-description">${item.description}</p>
                        <a href="#" class="btn btn-primary">View Details</a>
                        <form action="/wishlist/delete" method="post">
                        	<input type="hidden" value="${item.id}" name="productId">
                        	<button class="btn btn-danger">Remove from Wishlist</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
