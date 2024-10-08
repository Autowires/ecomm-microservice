<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Reviews</title>
<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>

<!-- Custom CSS -->
<style>
body {
	background-color: #f8f9fa;
}

/* Reviews Header */
.reviews-header {
	text-align: center;
	margin-bottom: 30px;
	font-size: 2rem;
	font-weight: 600;
	color: #343a40;
}

/* Table Styles */
.table {
    margin-top: 20px;
    border-radius: 8px;
    overflow: hidden;
}

/* Table Header */
.table thead th {
    background-color: #007bff;
    color: white;
}

/* Table Row Hover Effect */
.table tbody tr:hover {
    background-color: #f1f1f1;
}

/* No Reviews Message */
.no-reviews {
	text-align: center;
	font-size: 1.5rem;
	font-weight: 500;
	color: #777;
	margin-top: 50px;
}

/* Star Rating */
.star-rating i {
    color: #ffcc00;
    margin-right: 2px;
}

/* Remove Button Style */
.btn-danger {
    background-color: #dc3545;
    border-color: #dc3545;
    transition: background-color 0.3s ease;
}

.btn-danger:hover {
    background-color: #c82333;
    border-color: #bd2130;
}
</style>
</head>
<body>

<!-- Main Navigation -->
<%@include file="../header.jsp"%>

<!-- Customer Reviews Section -->
<section>
	<div class="container">
		<c:choose>
			<c:when test="${empty reviews}">
				<div class="no-reviews">No reviews have been posted yet.</div>
			</c:when>
			<c:otherwise>
				<div class="reviews-header">Customer Reviews</div>

				<!-- Reviews Table -->
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Review Content</th>
							<th>Rating</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="review" items="${reviews}">
							<tr>
								<td>${review.productName}</td>
								<td>${review.reviewContent}</td>
								<td>
									<div class="star-rating">
										<c:forEach begin="1" end="${review.rating}" varStatus="loop">
											<i class="fas fa-star"></i>
										</c:forEach>
									</div>
								</td>
								<td>
									<form action="/reviews/delete" method="post" style="display:inline;">
										<input type="hidden" name="reviewId" value="${review.reviewId}">
										<button type="submit" class="btn btn-danger btn-sm">Remove</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</section>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
