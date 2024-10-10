<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<style>
body {
	background-color: #f8f9fa;
}

.form-container {
	margin-top: 50px;
}

.card-header {
	background-color: #007bff;
	color: white;
}

.btn-primary {
	background-color: #007bff;
}
</style>
</head>
<body>
	<!-- Include Header -->
	<jsp:include page="../header.jsp" />

	<div class="container form-container">
		<div class="row justify-content-center">
			<div class="col-md-8 col-lg-7">
				<div class="card mb-4">
					<div class="card-header">
						<h4>Checkout</h4>
					</div>
					<div class="card-body">
						<!-- Billing Address Form -->
						<h5>Billing Address</h5>
						<form method="post" id="checkout-form">
							<div class="mb-3">
								<label for="name" class="form-label">Full Name</label> <input
									type="text" class="form-control" id="name" name="name" required>
							</div>
							<div class="mb-3">
								<label for="address" class="form-label">Address</label>
								<textarea class="form-control" id="address" name="address"
									required></textarea>
							</div>

							<div class="mb-3">
								<label for="city" class="form-label">City</label> <input
									type="text" class="form-control" id="city" name="city" required>
							</div>
							<div class="mb-3">
								<label for="phone" class="form-label">Phone Number</label> <input
									type="text" class="form-control" id="phone" name="phone"
									required>
							</div>
							<hr>
							<h5>Payment Method</h5>
							 <div class="mb-3">
								<label for="paymentType" class="form-label">Select
									Payment Type</label> <select class="form-select" id="paymentType"
									name="paymentType" required>
									<option value="cash">Cash on Delivery</option>
								</select>
								
							</div> 
							<form><script src="https://checkout.razorpay.com/v1/payment-button.js" data-payment_button_id="pl_P6C9prLtlRt6EX" async> </script> </form>
							
							
						</form>
						<button type="submit" class="btn btn-primary w-100" form="checkout-form">Confirm
								Order</button>
					</div>
				</div>
			</div>

			<div class="col-md-8 col-lg-5">
				<div class="card">
					<div class="card-header">
						<h4>Order Summary</h4>
					</div>
					<div class="card-body">
						<c:set var="totalPrice"></c:set>
						<p class="mb-1">
							<strong>Items in Cart:</strong>
						</p>
						<ul>
							<c:forEach var="item" items="${cartItems}">
								<c:set var="totalPrice"
									value="${item.price * item.quantity + totalPrice}"></c:set>
								<li>${item.productName}- $${item.price} x ${item.quantity}</li>
							</c:forEach>
						</ul>
						<p class="mb-1">
							<strong>Total:</strong> $${totalPrice}
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>



