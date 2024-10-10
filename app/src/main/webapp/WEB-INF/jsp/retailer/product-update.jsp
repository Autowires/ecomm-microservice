<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Product</title>
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
    background-color: #28a745;
}

.card-header h4 {
    margin: 0;
}

.btn-success {
    background-color: #28a745;
}

.btn-danger {
    background-color: #dc3545;
}
</style>
</head>
<body>
    <!-- Include Header -->
    <%@include file="header.jsp"%>

    <div class="container form-container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4>Update Product</h4>
                    </div>
                    <div class="card-body">
                        <!-- Form for updating a product with pre-populated values -->
                        <form method="post" id="update-form">
                            <!-- Hidden input for product ID -->
                            <input type="hidden" name="id" value="${product.id}">

                            <div class="row">
                                <!-- Left Column -->
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="title" class="form-label">Product Title</label> 
                                        <input type="text" class="form-control" id="title" name="title"
                                            value="${product.title}" required maxlength="100">
                                    </div>
                                    <div class="mb-3">
                                        <label for="description" class="form-label">Description</label>
                                        <textarea class="form-control" id="description"
                                            name="description" rows="3" maxlength="500" required>${product.description}</textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="price" class="form-label">Price</label> 
                                        <input type="number" step="0.01" class="form-control" id="price"
                                            name="price" value="${product.price}" required min="0">
                                    </div>
                                </div>

                                <!-- Right Column -->
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="category" class="form-label">Category</label> 
                                        <input type="text" class="form-control" id="category"
                                            name="category" value="${product.category}" required maxlength="50">
                                    </div>
                                    <div class="mb-3">
                                        <label for="stock" class="form-label">Stock Quantity</label> 
                                        <input type="number" class="form-control" id="stock" name="stock"
                                            value="${product.stock}" required min="0">
                                    </div>
                                    <div class="mb-3">
                                        <label for="imageUrl" class="form-label">Image URL</label> 
                                        <input type="url" class="form-control" id="imageUrl"
                                            name="imageUrl" value="${product.imageUrl}">
                                    </div>
                                </div>
                            </div>
                            <c:if test="${not empty errorMessage}">
                                <p id="message" class="text-center mt-3">${errorMessage}</p>
                            </c:if>
                        </form>
            
                        <div class="d-flex justify-content-between mt-4">
                            <button type="submit" class="btn btn-success" form="update-form">Update
                                Product</button>
                            <form method="POST" action="/retailer/inventory/deleteProduct"
                                style="display: inline;">
                                <input type="hidden" name="_method" value="DELETE"> 
                                <input type="hidden" name="productId" value="${product.id}">
                                <button type="submit" class="btn btn-delete">
                                    <i class="fas fa-trash"></i> Delete
                                </button>
                            </form>
                        </div>

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
