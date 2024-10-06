<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Invoice</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            padding: 20px;
        }

        .invoice-box {
            background-color: white;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px;
        }

        .invoice-header {
            border-bottom: 1px solid #007bff;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        .invoice-header h2 {
            color: #007bff;
        }

        .table th {
            background-color: #007bff;
            color: white;
        }

        .text-right {
            text-align: right;
        }

        .print-btn {
            margin-top: 20px;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
        }

        .print-btn:hover {
            background-color: #0056b3;
        }

        @media print {
            .print-btn {
                display: none;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="invoice-box">
            <!-- Invoice Header -->
            <div class="invoice-header">
            	<p>ZIP Stores</p>
                <h2>Order Invoice</h2>
                <p>Order Number: <strong>#${order.orderId}</strong></p>
                <p>Order Date: <strong>${order.orderDate}</strong></p>
            </div>

            <!-- Customer Information -->
            <div class="row mb-4">
                <div class="col-md-6">
                    <h6>Customer Information:</h6>
                    <p>
                        <strong>${order.Buyername}</strong><br>
                        ${order.address}<br>
                        Payment Method: ${order.paymentType}
                    </p>
                </div>
                
            </div>

            <!-- Product Details -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Total Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${order.orderItems}">
                        <tr>
                            <td>${item.productName}</td>
                            <td>${item.quantity}</td>
                            <td>$${item.price}</td>
                            <td>$${item.price * item.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Order Summary -->
            <div class="row">
                <div class="col-md-6"></div>
                <div class="col-md-6 text-right">
                    <p><strong>Subtotal: </strong>$${order.totalAmount}</p>
                    <p><strong>Total Amount: </strong><span class="text-primary">$${order.totalAmount}</span></p>
                </div>
            </div>

            <!-- Print Button -->
            <div class="text-right">
                <button class="print-btn" onclick="window.print()">Print Invoice</button>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
