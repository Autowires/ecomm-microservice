<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Zip</title>
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
</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- Jumbotron -->
	<div class="bg-primary text-white py-5">
		<div class="container py-5">
			<h1>
				Best products & <br />brands in our store
			</h1>
			<p>Trendy Products, Factory Prices, Excellent Service</p>
			<a href="/register-retailer" class="btn btn-outline-light">Retailer
				registration</a> <a href="/products">
				<button type="button"
					class="btn btn-light text-primary border border-white">
					<span>Purchase now</span>
				</button>
			</a>
		</div>
	</div>
	<!-- Jumbotron End -->

	<!-- New Products Carousel -->
	<section class="new-products my-5">
		<div class="container">
			<h3>New Products</h3>
			<div class="container">
				<div class="row">
					<c:forEach var="product" items="${newProducts}">
						<div class="col-md-2">
							<div class="card text-center">
								<img src="${product.imageUrl}" class="img-fluid"
									style="width: 150px; height: 150px;" alt="${product.title}" />
								<div class="card-body">
									<h6 class="card-title">${product.title}</h6>
									<p class="card-text">Price: ₹ ${product.price}</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>


		</div>
		</div>
		</div>
	</section>
	<!-- New Products Carousel End -->

	<!-- Products Section -->
	<section>
		<div class="container my-5">
			<header class="mb-4">
				<h3>Categories</h3>
			</header>
			<div class="row">
				<!-- Card Template Start -->
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/cameras.jpg" class="card-img-top"
							style="aspect-ratio: 1/1" alt="Cameras"
							onClick="onCategoryClick('cameras')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Cameras</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>
				<!-- Repeat for other products -->
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/headphones.jpg" class="card-img-top"
							style="aspect-ratio: 1/1" alt="Headphones"
							onClick="onCategoryClick('headphones')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Headphones</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/smartwatches.jpg" class="card-img-top"
							style="aspect-ratio: 1/1" alt="Smart Watches"
							onClick="onCategoryClick('smartwatches')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Smart Watches</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/huawei-p50.jpg" class="card-img-top"
							style="aspect-ratio: 1/1" alt="Mobile Phones"
							onClick="onCategoryClick('mobilephone')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Mobile Phones</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/menswear.webp" class="card-img-top"
							style="aspect-ratio: 1/1" alt="Mens Fashion"
							onClick="onCategoryClick('mensfashion')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Mens Fashion</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/womenswear.webp" class="card-img-top"
							style="aspect-ratio: 1/1" alt="Women Fashion"
							onClick="onCategoryClick('womenfashion')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Womens Fashion</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/kids1.webp" class="card-img-top"
							style="aspect-ratio: 1/1" alt="kids fashion"
							onClick="onCategoryClick('kidsfashion')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Kids Fashion</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
					<div class="card w-100 my-2 shadow">
						<img src="img/fashionacces.jpg" class="card-img-top"
							style="aspect-ratio: 1/1" alt="fashion accessories"
							onClick="onCategoryClick('fashionaccessories')" />
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">Fashion Accessories</h5>
							<div class="card-footer mt-auto"></div>
						</div>
					</div>
				</div>

				<!-- Continue for other categories -->
				<script type="text/javascript">
					function onCategoryClick(category) {
						// Check if user is logged in
						window.location.href = "/products?category="
								+ encodeURIComponent(category);
					}
				</script>
			</div>
		</div>
	</section>
	<!-- Products Section End -->

	<!-- Feature Section -->
	<section class="mt-5 bg-light">
		<div class="container text-dark pt-3">
			<header class="pt-4 pb-3">
				<h3>Why choose us</h3>
			</header>
			<div class="row mb-4">
				<div class="col-lg-4 col-md-6">
					<figure class="d-flex align-items-center mb-4">
						<span class="rounded-circle bg-white p-3 d-flex me-2 mb-2">
							<i class="fas fa-camera-retro fa-2x text-primary"></i>
						</span>
						<figcaption class="info">
							<h6 class="title">Reasonable prices</h6>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
								sed do eiusmor.</p>
						</figcaption>
					</figure>
				</div>
				<div class="col-lg-4 col-md-6">
					<figure class="d-flex align-items-center mb-4">
						<span class="rounded-circle bg-white p-3 d-flex me-2 mb-2">
							<i class="fas fa-star fa-2x text-primary"></i>
						</span>
						<figcaption class="info">
							<h6 class="title">Best quality</h6>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
								sed do eiusmor.</p>
						</figcaption>
					</figure>
				</div>
				<div class="col-lg-4 col-md-6">
					<figure class="d-flex align-items-center mb-4">
						<span class="rounded-circle bg-white p-3 d-flex me-2 mb-2">
							<i class="fas fa-plane fa-2x text-primary"></i>
						</span>
						<figcaption class="info">
							<h6 class="title">Worldwide shipping</h6>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
								sed do eiusmor.</p>
						</figcaption>
					</figure>
				</div>
			</div>
		</div>
	</section>
	<!-- Feature Section End -->

	<jsp:include page="footer.jsp" />

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.min.js"></script>

</body>
</html>
