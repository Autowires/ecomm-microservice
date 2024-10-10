<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Information</title>
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
<style>
.product-image {
	width: 400px;
	height: 400px;
	object-fit: cover;
}

.review-container {
	background-color: #f9f9f9;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 5px;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>

	<!-- Main Container -->
	<div class="container my-5">
		<div class="row">
			<!-- Product Image (Left Side) -->
			<div class="col-lg-6">
				<img src="${product.imageUrl}" alt="${product.title}"
					class="img-fluid product-image">
				<!-- Buttons below the image -->
				<div class="mt-3" style="display: flex">
					<form action="/cart" method="post">
						<input type="hidden" name="productId" value="${product.id}">
						<button type="submit" class="btn btn-primary me-2">Add to
							Cart</button>
					</form>
					<form action="/wishlist/add" method="post">
						<input type="hidden" name="productId" value="${product.id}">
						<button type="submit" class="btn btn-outline-secondary">Add
							to Wishlist</button>
					</form>
				</div>
			</div>

			<!-- Product Info (Right Side) -->
			<div class="col-lg-6">
				<h2>${product.title}</h2>
				<p class="text-muted">${product.description}</p>
				<h4 class="text-success">Price: ₹${product.price}</h4>

				<!-- Display average rating -->
				<c:set var="totalRating" value="0" />
				<c:set var="count" value="0" />

				<%-- 
            <c:forEach var="review" items="${product.reviews}">
                <c:set var="totalRating" value="${totalRating + review.rating}"/>
                <c:set var="count" value="${count + 1}"/>
            </c:forEach>
            <c:set var="averageRating" value="${totalRating / count}"/>
            <h4 class="text-warning" margin-top:9%>Average Rating: <h4>${averageRating} / 5</h4></h4>
            --%>

				<!-- Customer Reviews Section -->
				<h4 style="margin-top: 9%">Customer Reviews</h4>
				<c:choose>
					<c:when test="${not empty reviews}">
						<c:forEach var="review" items="${reviews}">
							<div class="review-container">
								<strong>${review.customer.username}</strong>
								<strong>Rating: ${review.rating}/5</strong>
								<p>${review.reviewContent}</p>
								<small class="text-muted">Reviewed on
									${review.createdAt}</small>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p>No reviews for this product yet.</p>
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="${role == 'CUSTOMER'}">
				<div class="row mt-5">
					<div class="col-lg-6 offset-lg-3">
						<h4>Submit Your Review</h4>
						<form action="/reviews/add" method="post">
							<!-- Hidden field for product ID -->
							<input type="hidden" name="productId" value="${product.id}">

							<!-- Review Content -->
							<div class="mb-3">
								<label for="reviewContent" class="form-label">Review
									Content</label>
								<textarea class="form-control" id="reviewContent"
									name="reviewContent" rows="4"
									placeholder="Write your review here..." required></textarea>
							</div>

							<!-- Rating Selection -->
							<div class="mb-3">
								<label for="rating" class="form-label">Rating</label> <select
									class="form-select" id="rating" name="rating" required>
									<option value="" disabled selected>Select a rating</option>
									<option value="1">1 - Very Poor</option>
									<option value="2">2 - Poor</option>
									<option value="3">3 - Average</option>
									<option value="4">4 - Good</option>
									<option value="5">5 - Excellent</option>
								</select>
							</div>

							<!-- Submit Button -->
							<button type="submit" class="btn btn-success">Submit
								Review</button>
						</form>
					</div>
				</div>
			</c:if>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
