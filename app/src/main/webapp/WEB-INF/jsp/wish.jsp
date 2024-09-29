<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Wishlist</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- FontAwesome for icons -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>

<!-- Custom CSS for Styling -->
<style>
body {
	background-color: #f8f9fa;
}

/* Wishlist Header */
.wishlist-header {
	text-align: center;
	margin-bottom: 30px;
	font-size: 2rem;
	font-weight: 600;
	color: #343a40;
}

.wishlist-card {
	border: 1px solid #e0e0e0;
	transition: transform 0.2s, box-shadow 0.2s;
	border-radius: 8px;
	background-color: #fff;
	overflow: hidden;
}

.wishlist-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.wishlist-img {
	object-fit: cover;
	height: 200px;
	border-bottom: 1px solid #e0e0e0;
}

.wishlist-info {
	padding: 15px;
}

.wishlist-title {
	font-size: 1.1rem;
	font-weight: 500;
	margin-bottom: 0;
	color: #333;
	text-decoration: none;
}

.wishlist-title:hover {
	color: #007bff;
}

.wishlist-price {
	font-size: 1rem;
	color: #28a745;
	font-weight: 500;
	margin-top: 8px;
}

/* Remove button styling */
.remove-btn {
	font-size: 0.9rem;
	background-color: #dc3545;
	color: white;
	border: none;
	padding: 6px 12px;
	transition: background-color 0.3s;
}

.remove-btn:hover {
	background-color: #c82333;
}

.d-flex-between {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.container {
	margin-top: 30px;
}

@media ( max-width : 768px) {
	.wishlist-card {
		margin-bottom: 20px;
	}
}
/* Empty wishlist message styling */
.empty-wishlist {
	text-align: center;
	font-size: 1.5rem;
	font-weight: 500;
	color: #777;
	margin-top: 50px;
}
</style>
</head>
<body>
	<!--Main Navigation-->
	<%@include file="header.jsp"%>

	<!-- Wishlist Section -->
	<section>
		<c:choose>
			<c:when test="${empty wishlist}">

				<div class="empty-wishlist">Your wishlist is empty.</div>
			</c:when>
			<c:otherwise>
				<div class="wishlist-header">Your Wishlist</div>
			</c:otherwise>
		</c:choose>


		<div class="container">
			<!-- Wishlist Header -->

			<!-- Display All Products -->
			<div class="row">

				<c:forEach var="item" items="${wishlist}">
					<div class="col-md-4 mb-4">
						<div class="card wishlist-card">
							<img src="path_to_image/${item.image}"
								class="card-img-top wishlist-img" alt="Product Image">
							<div class="wishlist-info">
								<!-- Title and Remove button in the same row -->
								<div class="d-flex-between">
									<!-- Title as a link -->
									<a href="/products/${item.productId}" class="wishlist-title">${item.productName}</a>
									<!-- Remove from Wishlist button -->
									<form action="/wishlist/delete" method="post">
										<input type="hidden" value="${item.productId}"
											name="productId">
										<button type="submit" class="remove-btn">Remove</button>
									</form>
								</div>
								<!-- Product price -->
								<p class="wishlist-price">$${item.price}</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>


	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
