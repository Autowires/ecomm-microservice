<%@page import="com.microservices.app.dto.CartDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set"%>
<%@ page import="com.microservices.app.dto.CartDto"%>
<%@ page import="com.microservices.app.dto.CartItemDto"%>
<%@ page import="com.microservices.app.dto.ProductDto" %>>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<Style>
.cart-container {
    width: 80%;
    margin: auto;
}

.cart-items {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
}

.cart-item-card {
    border: 1px solid #ccc;
    padding: 15px;
    width: 30%;
    box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
    background-color: #f9f9f9;
}

.cart-total {
    margin-top: 20px;
    padding: 15px;
    border-top: 2px solid #ccc;
    position: fixed;
    bottom: 20px;
    left: 20px;
    background-color: #fff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.order-btn {
    padding: 10px 20px;
    background-color: #28a745;
    color: #fff;
    border: none;
    cursor: pointer;
}

.order-btn:hover {
    background-color: #218838;
}
</Style>
</head>
<body>
<div class="cart-container">
    <h2>Your Cart</h2>
    <div class="cart-items">
    <%
    CartDto cartDto=(CartDto) request.getAttribute("cartItems");
    Set<CartItemDto> items=cartDto.getItems();
    cartDto.getBuyerId();
    
    double totalPrice=0;
    for(CartItemDto item:items){
    
    	ProductDto product=item.getProduct();
    	double subtotal=product.getPrice()*item.getQuantity();
    	totalPrice=totalPrice+subtotal;
    
    %>
   <div class="cart-item-card">
   <h3><%= product.getProductName() %></h3>
   <p>Description:<%=product.getDescription() %> </p>
  <p>Price:<%= product.getPrice() %></p>
   <p>Quantity:<%=item.getQuantity() %></p>
   <p>Subtotal:<%=subtotal %></p>
   </div>
    <%
    }
    %>
    </div>
    <!-- Total Price calculation -->
   <div class="cart-total">
   <h3>Total Price:<%= totalPrice %></h3>
  
   </div>
   <form action="orders.jsp" method="POST">
            <input type="hidden" name="buyerId" value="<%= cartDto.getBuyerId() %>" />
            <input type="text" name="totalAmount" value=<%= totalPrice %>" readonly />
            <button class="order-btn" type="submit">Place Order</button>
        </form>
   </div>
</body>
</html>