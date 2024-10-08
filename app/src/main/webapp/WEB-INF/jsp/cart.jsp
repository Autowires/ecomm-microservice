<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Cart</title>
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
</style>
</head>
<body>
	<!--Main Navigation-->
	<%@include file="header.jsp"%>

	<!-- cart + summary -->
	<section class="bg-light my-5">
		<div class="container">
			<div class="row">
				<!-- cart -->
				<div class="col-lg-9">
					<div class="card border shadow-0">
						<div class="m-4">
							<h4 class="card-title mb-4">Your shopping cart</h4>
							<c:set var="totalPrice"></c:set>
							<c:forEach var="item" items="${cartItems}">
								<c:set var="totalPrice"
									value="${item.price * item.quantity + totalPrice}"></c:set>
								<div class="row gy-3 mb-4">
									<div class="col-lg-5">
										<div class="me-lg-5">
											<div class="d-flex">
												<img
													src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/11.webp"
													class="border rounded me-3"
													style="width: 96px; height: 96px;" alt="Winter jacket" />
												<div class="">
													<a href="#" class="nav-link">${item.productName}</a>
												</div>
											</div>
										</div>
									</div>
									<div
										class="col-lg-2 col-sm-6 col-6 d-flex flex-row flex-lg-column flex-xl-row text-nowrap">
										<div class="">
											<form method="POST">
												<input name="_method" value="PUT" type="hidden"> <input
													name="cartItemId" value="${item.id}" type="hidden">
												<input name="productId" value="${item.productId}"
													type="hidden"> <input name="quantity" type="number"
													value="${item.quantity}" class="form-input me-4"
													style="width: 100px;">
												<button>Update</button>
											</form>
										</div>
										<div class="">
											<p class="h6">${item.price * item.quantity}</p>
											<small class="text-muted text-nowrap"> $${item.price}
												/ per item </small>
										</div>
									</div>
									<div
										class="col-lg col-sm-6 d-flex justify-content-sm-center justify-content-md-start justify-content-lg-center justify-content-xl-end mb-2">
										<div class="float-md-end">
											<form method="POST">
												<input name="_method" value="DELETE" type="hidden">
												<input name="cartItemId" value="${item.id}" type="hidden">
												<button
													class="btn btn-light border text-danger icon-hover-danger">Remove</button>
											</form>
										</div>
									</div>
								</div>

							</c:forEach>

							<div class="border-top pt-4 mx-4 mb-4">
								<p>
									<i class="fas fa-truck text-muted fa-lg"></i> Free Delivery
									within 1-2 weeks
								</p>
								<p class="text-muted">Lorem ipsum dolor sit amet,
									consectetur adipisicing elit, sed do eiusmod tempor incididunt
									ut labore et dolore magna aliqua. Quis ipsum suspendisse
									ultrices gravida. Risus commodo viverra maecenas accumsan.</p>
							</div>
						</div>
					</div>
					<!-- cart -->

					<!-- summary -->
					<div class="col-lg-3">
						<div class="card shadow-0 border">
							<div class="card-body">
								<div class="d-flex justify-content-between">
									<p class="mb-2">Total price:</p>
									<p class="mb-2">$${totalPrice}</p>
								</div>

								<div class="d-flex justify-content-between">
									<p class="mb-2">Discount:</p>
									<p class="mb-2 text-success">0</p>
								</div>

								<div class="d-flex justify-content-between">
									<p class="mb-2">TAX:</p>
									<p class="mb-2">0</p>
								</div>

								<hr />

								<div class="d-flex justify-content-between">
									<p class="mb-2">Total price:</p>
									<p class="mb-2 fw-bold">$${totalPrice}</p>
								</div>

								<div class="mt-3">
									<a href="/checkout" class="btn btn-success w-100 shadow-0 mb-2">
										Make Purchase </a> <a href="/products"
										class="btn btn-outline-primary w-100 border mt-2"> Back to
										shop </a>
								</div>
							</div>
						</div>
					</div>
					<!-- summary -->
				</div>
			</div>
	</section>



	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
