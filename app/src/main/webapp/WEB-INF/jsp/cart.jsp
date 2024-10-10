<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

    <!-- Custom CSS for Styling -->
    <style>
        body {
            background-color: #f8f9fa;
        }

        .out-of-stock-msg {
            color: red;
            font-weight: bold;
        }

        .low-stock-msg {
            color: orange;
        }

        .disabled-btn {
            background-color: grey;
            pointer-events: none;
            opacity: 0.6;
        }

        .cart-img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
      <!-- Main Navigation -->
    <%@include file="header.jsp"%>

    <!-- Cart + Summary -->
    <section class="bg-light my-5">
        <div class="container">
            <div class="row">
                <!-- Cart -->
                <div class="col-lg-9 mb-4">
                    <div class="card border shadow-0">
                        <div class="m-4">
                            <h4 class="card-title mb-4">Your shopping cart</h4>
                            <c:set var="totalPrice" value="0" />
                            <c:set var="outOfStock" value="false" />
                            <c:forEach var="item" items="${cartItems}">
                                <!-- If stock is greater than 0, include in total price calculation -->
                                <c:if test="${item.stock > 0}">
                                    <c:set var="totalPrice" value="${totalPrice + (item.price * item.quantity)}" />
                                </c:if>

                                <!-- If stock is zero, mark outOfStock as true -->
                                <c:if test="${item.stock == 0}">
                                    <c:set var="outOfStock" value="true" />
                                </c:if>

                                <div class="row gy-3 mb-4">
                                    <div class="col-md-3 col-4">
                                        <img src="${item.productImageUrl}" 
                                            class="cart-img border rounded me-3" alt="Product Image">
                                    </div>
                                    <div class="col-md-5 col-8">
                                        <a href="#" class="nav-link">${item.productName}</a>
                                        <!-- Check for stock and display messages -->
                                        <c:choose>
                                            
                                            <c:when test="${item.stock == 0}">
                                                <p class="out-of-stock-msg">Product is out of stock. Remove this product to place an order.</p>
                                            </c:when>
                                            
                                            <c:when test="${item.stock > 0 && item.stock < 5}">
                                                <p class="low-stock-msg">Only ${item.stock} left in stock!</p>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-2 col-6 text-nowrap">
                                        <p class="h6">${item.price * item.quantity}</p>
                                        <small class="text-muted"> $${item.price} / per item </small>
                                    </div>
                                    <div class="col-md-2 col-6 text-end">
                                        <form method="POST">
                                            <input name="_method" value="DELETE" type="hidden">
                                            <input name="cartItemId" value="${item.id}" type="hidden">
                                            <button class="btn btn-light border text-danger icon-hover-danger">Remove</button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="border-top pt-4">
                                <p><i class="fas fa-truck text-muted fa-lg"></i> Free Delivery within 1-2 weeks</p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Summary -->
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

                            <!-- Disable Purchase button if any item is out of stock -->
                            <div class="mt-3">
                            	<c:if test="${!outOfStock}">
                            		<a href="/checkout" class="btn btn-success w-100 shadow-0 mb-2">
                            			Make Purchase
                                		</a>	
                            	</c:if>
                                
                                <a href="/products" class="btn btn-outline-primary w-100 border mt-2">Back to shop</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
