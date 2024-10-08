<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Inventory</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f5f5f5;
        }

        .inventory-header {
            background-color: #007bff;
            color: white;
            padding: 15px;
        }

        .inventory-header h2 {
            margin: 0;
        }

        .add-product-btn {
            background-color: #28a745;
            color: white;
            margin-bottom: 20px;
        }

        .product-table th, .product-table td {
            text-align: center;
            vertical-align: middle;
        }

        .action-buttons .btn {
            margin: 0 5px;
        }

        .btn-update {
            background-color: #007bff;
            color: white;
        }

        .btn-delete {
            background-color: #dc3545;
            color: white;
        }

        .btn-delete:hover {
            background-color: #c82333;
        }

        .btn-update:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <!-- Include header -->
    <%@include file="header.jsp"%>

    <!-- Inventory Header Section -->
    <header class="inventory-header">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h2>Retailer Inventory</h2>
                <a href="inventory/add-product" class="btn add-product-btn">
                    <i class="fas fa-plus-circle"></i> Add New Product
                </a>
            </div>
        </div>
    </header>

    <!-- Product Inventory Table -->
    <section class="container my-5">
        <div class="card shadow-sm">
            <div class="card-body">
                <table class="table table-hover product-table">
                    <thead class="table-primary">
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Stock</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.title}</td>
                                <td>${product.description}</td>
                                <td>$${product.price}</td>
                                <td>${product.category}</td>
                                <td>${product.stock}</td>
                                <td>
                                    <img src="${product.imageUrl}" alt="${product.title}" class="img-thumbnail" style="width: 60px; height: 60px;">
                                </td>
                                <td class="action-buttons">
                                	<a href="inventory/${product.id}/updateProduct" class="btn btn-update">Update</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
