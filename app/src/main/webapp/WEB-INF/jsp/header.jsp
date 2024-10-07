<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<!-- FontAwesome for icons -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">

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

.user-menu i {
	font-size: 1.2rem;
}

.user-menu .nav-link p {
	font-size: 0.9rem;
	margin-left: 0.5rem;
}

.bg-white {
	background-color: #fff !important;
}

.input-group button {
	border-top-left-radius: 0;
	border-bottom-left-radius: 0;
}

.input-group input {
	border-right: none;
}
</style>
</head>
<body>
	<!--Main Navigation-->
	<header>
		<!-- Jumbotron -->
		<div class="p-3 text-center bg-white">
			<div class="container">
				<div class="row gy-3 align-items-center">
					<!-- Left elements: Logo -->
					<div class="col-lg-2 col-md-3 col-sm-4 col-4">
						<a href="#" target="_blank" class="header-logo float-start"> <img
							src="/img/zip.png" alt="Logo">
						</a>
					</div>

					<!-- Center elements: User Info and Links -->
					<div class="col-lg-5 col-md-5 col-sm-12 col-12">
						<form action="/products" method="get">
							<div class="input-group">
								<input type="search" class="form-control" placeholder="Search"
									name="search" />
								<button type="submit" class="btn btn-primary">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</form>
					</div>

					<!-- Right elements: User Menu -->
					<div class="col-lg-5 col-sm-8 col-8 ms-auto user-menu">
						<div class="d-flex justify-content-end">
							<!-- Conditional user login state -->
							<a href="/products"
								class="me-3 nav-link d-flex align-items-center">
								<p>Browse products</p>
							</a>
							<c:choose>
								<c:when test="${empty role}">
									<a href="/login"
										class="me-3 nav-link d-flex align-items-center">
										<p>Login</p>
									</a>
									<a href="/register"
										class="me-3 nav-link d-flex align-items-center">
										<p>Signup</p>
									</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="me-3 nav-link d-flex align-items-center">
										<p>
											Welcome, <b>${sessionScope.username}</b>
										</p>
									</a>
								</c:otherwise>
							</c:choose>
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
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarLeftAlignExample"
					aria-controls="navbarLeftAlignExample" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-bars"></i>
				</button>

				<!-- Navbar Links -->
				<div class="collapse navbar-collapse" id="navbarLeftAlignExample">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					
						<c:if test="${role == 'CUSTOMER'}">
							<!-- Role-based navigation -->

							<a href="/wishlist"
								class="me-3 nav-link d-flex align-items-center">
								<p>Wishlist</p>
							</a>
							<a href="/reviews"
								class="me-3 nav-link d-flex align-items-center">
								<p>Reviews</p>
							</a>
							<a href="/cart" class="me-3 nav-link d-flex align-items-center">
								<p>My Cart</p>
							</a>
							<a href="/orders" class="me-3 nav-link d-flex align-items-center">
								<p>My Orders</p>
							</a>
							<a href="/logout" class="nav-link d-flex align-items-center">
								<p>Logout</p>
							</a>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
		<!-- Navbar End -->
	</header>

	<!-- Bootstrap JS (Bundle with Popper) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
