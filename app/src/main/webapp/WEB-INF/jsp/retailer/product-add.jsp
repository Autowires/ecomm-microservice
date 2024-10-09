<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Product</title>
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
}

.card-header h4 {
	margin: 0;
}

.btn-primary {
	background-color: #007bff;
}
</style>
</head>
<body>
	<!-- Include Header -->
	<jsp:include page="header.jsp" />

	<div class="container form-container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">
						<h4>Add New Product</h4>
					</div>
					<%--<div class="card-body">
						<!-- Form for adding a new product with two-column layout -->
				 <form method="post">
							<div class="row">
								<!-- Left Column -->
								<div class="col-md-6">
									<div class="mb-3">
										<label for="title" class="form-label">Product Title</label> <input
											type="text" class="form-control" id="title" name="title"
											required>
									</div>
									<div class="mb-3">
										<label for="description" class="form-label">Description</label>
										<textarea class="form-control" id="description"
											name="description" rows="3"></textarea>
									</div>
									<div class="mb-3">
										<label for="price" class="form-label">Price</label> <input
											type="number" step="0.01" class="form-control" id="price"
											name="price" required>
									</div>
								</div>

								<!-- Right Column -->
								<div class="col-md-6">
									<div class="mb-3">
										<label for="category" class="form-label">Category</label> <input
											type="text" class="form-control" id="category"
											name="category" required>
									</div>
									<div class="mb-3">
										<label for="stock" class="form-label">Stock Quantity</label> <input
											type="number" class="form-control" id="stock" name="stock"
											required>
									</div>
									<div class="mb-3">
										<label for="imageUrl" class="form-label">Image URL</label> <input
											type="text" class="form-control" id="imageUrl"
											name="imageUrl">
									</div>
								</div>
							</div>
							<c:if test="${not empty errorMessage}">
								<p id="message" class="text-center mt-3">${errorMessage}</p>
							</c:if>
							<!-- Submit Button -->
							<div class="mt-4">
								<button type="submit" class="btn btn-primary w-100">Add
									Product</button>
							</div>
	
						</form> --%>
						
	<div class="card-body">
		<!-- Form for adding a new product with two-column layout -->
	<form method="post" novalidate>
    <div class="row">
        <!-- Left Column -->
        <div class="col-md-6">
            <div class="mb-3">
                <label for="title" class="form-label">Product Title</label> 
                <input type="text" class="form-control" id="title" name="title" required>
                <div class="invalid-feedback">Please enter a product title.</div>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                <div class="invalid-feedback">Please enter a description.</div>
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price</label> 
                <input type="number" step="0.01" class="form-control" id="price" name="price" required min="0">
                <div class="invalid-feedback">Please enter a valid price greater than or equal to 0.</div>
            </div>
        </div>

        <!-- Right Column -->
        <div class="col-md-6">
            <div class="mb-3">
                <label for="category" class="form-label">Category</label> 
                <input type="text" class="form-control" id="category" name="category" required>
                <div class="invalid-feedback">Please enter a category.</div>
            </div>
            <div class="mb-3">
                <label for="stock" class="form-label">Stock Quantity</label> 
                <input type="number" class="form-control" id="stock" name="stock" required min="1">
                <div class="invalid-feedback">Please enter a stock quantity of at least 1.</div>
            </div>
            <div class="mb-3">
                <label for="imageUrl" class="form-label">Image URL</label> 
                <input type="url" class="form-control" id="imageUrl" name="imageUrl">
                <div class="invalid-feedback">Please enter a valid image URL.</div>
            </div>
        </div>
    </div>

    <!-- Submit Button -->
    <div class="mt-4">
        <button type="submit" class="btn btn-primary w-100">Add Product</button>
    </div>
</form>
</div>

<script>
   
    (function () {
        'use strict';

        
        var form = document.querySelector('form');

       
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    })();
</script>
						
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
