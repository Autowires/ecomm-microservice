<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Orders</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .table thead th {
            background-color: #007bff;
            color: white;
        }
        .btn-update {
            background-color: #007bff;
            color: white;
            border-radius: 20px;
            padding: 8px 16px;
        }
        .btn-update[disabled] {
            background-color: #6c757d; /* Grey out if disabled */
            border-color: #6c757d;
        }
        .card-body {
            padding: 10px;
        }
        .total-amount {
            font-size: 1.25rem;
            font-weight: bold;
        }
        .status-form {
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .form-select {
            width: 120px;
            border-radius: 20px;
            padding: 5px;
        }
    </style>
</head>
<body>
    <!-- Include Header -->
    <jsp:include page="../retailer/header.jsp" />

    <div class="container mt-4">
        <h2 class="mb-4">Your Product Orders</h2>
        
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Ordered date</th>
                    <th>Delivery Address</th>
                    <th>Payment Method</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Update Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <c:forEach var="item" items="${order.orderItems}">
                        <tr>
                            <!-- Order Info -->
                            <td>${order.orderId}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.address}</td>
                            <td>${order.paymentType}</td>
                            
                            <!-- Product Info -->
                            <td>${item.productName}</td>
                            <td>${item.quantity}</td>
                            <td>$${item.price * item.quantity}</td>
                            <td>
                                <span class="badge 
                                    <c:choose>
                                        <c:when test="${item.orderStatus == 'CANCELLED'}">bg-danger</c:when>
                                        <c:when test="${item.orderStatus == 'DELIVERED'}">bg-success</c:when>
                                        <c:otherwise>bg-primary</c:otherwise>
                                    </c:choose>">
                                    ${item.orderStatus}
                                </span>
                            </td>
                            
                            <!-- Update Status Form -->
                            <td>
                                <c:if test="${item.orderStatus != 'CANCELLED' && item.orderStatus != 'DELIVERED'}">
                                    <form action="/retailer/orders/update" method="POST" class="status-form">
                                    	<input type="hidden" name="orderItemId" value="${item.orderItemId}">
                                        <select name="status" class="form-select">
                                            <option value="PROCESSING" ${item.orderStatus == 'PROCESSING' ? 'selected' : ''}>Processing</option>
                                            <option value="SHIPPED" ${item.orderStatus == 'SHIPPED' ? 'selected' : ''}>Shipped</option>
                                            <option value="DELIVERED" ${item.orderStatus == 'DELIVERED' ? 'selected' : ''}>Delivered</option>
                                        </select>
                                        <button type="submit" class="btn btn-update">Update</button>
                                    </form>
                                </c:if>
                                <c:if test="${item.orderStatus == 'CANCELLED' || item.orderStatus == 'DELIVERED'}">
                                    <span class="text-muted">No actions available</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
