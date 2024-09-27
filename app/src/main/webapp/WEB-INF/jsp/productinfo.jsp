<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Information</title>
     <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <style>
        .product-image {
            width: 400px;
            height: 400px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<!-- Main Container -->
<div class="container my-5">
    <div class="row">
        <!-- Product Image (Left Side) ${product.imageUrl} -->
        <div class="col-lg-6">
            <img src="https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcT_e9HL4LSfgsToceVTm_dvON6d_lPVgkNUPxOUdi0kVtxWGKD_RKXG7pLdK89BHEd_PVpikq8GwEBRVe68mXa8QGZgratCFsHMzFPZqns&usqp=CAE" alt="${product.title}" class="img-fluid product-image">
            <!-- Buttons below the image -->
            <div class="mt-3" style="display:flex">
               <form action="/cart/addtocart/${session.userId}" method="post">
    				<input type="hidden" name="productId" value="${product.id} ">
    				<button type="submit" class="btn btn-primary me-2">Add to Cart</button>
			</form>
                <button type="button" class="btn btn-outline-secondary">Add to Wishlist</button>
            </div>
        </div>
        <!-- Product Info (Right Side) -->
        <div class="col-lg-6">
            <h2>${product.title}</h2>
            <p class="text-muted">${product.description}</p>
            <h4 class="text-success">Price: ₹${product.price}</h4>
            <h1>Ratings: ${product.rating} /5</h1>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
