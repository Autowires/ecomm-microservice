package com.microservices.orders.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservices.orders.entity.Product;
import com.microservices.orders.entity.Cart;
import com.microservices.orders.entity.OrderItem;
import com.microservices.orders.entity.Orders;
import com.microservices.orders.enums.OrderStatus;

@Service
public class OrderServiceImplementation  implements OrderService{
   @Autowired
	private CartService cartService;
   @Autowired
   private ProductService productService;
	@Override
	public Orders placeOrder(Orders order, Long buyerId) {
		Cart cart=cartService.getBuyerCartById(buyerId);
		Orders orderItems=createOrder(cart,order);
		 List<OrderItem> orderItemList = createOrderItems(orderItems, cart);
	}
	private List<OrderItem> createOrderItems(Orders orderItems, Cart cart) {
		 return  cart.getItems().stream().map(cartItem -> {
	            Product product =  productService.findProductById(cartItem.getProductId());//interservice communication
	            product.setStock()
	            productRepository.save(product);
	            return  new OrderItem(
	                    order,
	                    product,
	                    cartItem.getQuantity(),
	                    cartItem.getUnitPrice());
	        }).toList();
		return null;
	}
	private Orders createOrder(Cart cart,Orders order) {
		Orders orderItems=new Orders();
		orderItems.setBuyerId(cart.getBuyerId());
		orderItems.setOrderDate(LocalDate.now());
		orderItems.setDeliveryDate(LocalDate.now().plusDays(7));
		orderItems.setOrderStatus(OrderStatus.PENDING);
		orderItems.setAddress(order.getAddress());
		orderItems.setPaymentType(order.getPaymentType());
		return orderItems;
	}

}
