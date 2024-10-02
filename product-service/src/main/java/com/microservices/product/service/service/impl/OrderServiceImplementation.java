package com.microservices.product.service.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.OrderItemRepository;
import com.microservices.product.service.dao.OrdersRepository;
import com.microservices.product.service.dao.ProductRepository;
import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.dto.OrderDTO;
import com.microservices.product.service.entity.CartItem;
import com.microservices.product.service.entity.Customer;
import com.microservices.product.service.entity.OrderItem;
import com.microservices.product.service.entity.OrderStatus;
import com.microservices.product.service.entity.Orders;
import com.microservices.product.service.service.BuyerServvice;
import com.microservices.product.service.service.CartItemService;
import com.microservices.product.service.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImplementation implements OrderService {
	@Autowired
	private OrdersRepository orderRepository;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private BuyerServvice buyeService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private BuyerServvice buyerServvice;
	
	@Transactional
	@Override
	public Orders placeOrder(Orders order, Long buyerId) {
		// TODO Auto-generated method stub

		List<CartResponse> cartItems = cartItemService.getBuyerCartById(buyerId);
		Customer customer=buyerServvice.getBuyerById(buyerId);
		
		 double totalAmount = cartItems.stream()
                 .mapToDouble(item -> item.getQuantity() * item.getPrice())
                 .sum();
		
		 Orders orders = createOrders(order,customer);
		 orders.setTotalAmount(totalAmount);
		 
		 Set<OrderItem> orderItems = new HashSet<>();
		    for (CartResponse cartItem : cartItems) {
		    	var product = productRepository.findById(cartItem.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
		    	product.setStock(product.getStock()-cartItem.getQuantity());
		    	productRepository.save(product);
		        OrderItem orderItem = new OrderItem(orders, product, cartItem.getQuantity(), cartItem.getPrice());
		 
		        if(orderItem!=null) {
		        	System.out.println("Orderitem has values");
		        	System.out.println("orderItem price is "+orderItem.getPrice());
		        }
		        
		        orderItems.add(orderItem);
		    }
		    orders.setOrderItems(orderItems);
		    Orders savedOrder = orderRepository.save(orders);

		    // Optionally: Clear the buyer's cart after placing the order
		    if(savedOrder!=null) {
		    	 cartItemService.clearBuyerCart(buyerId);
		    }
		   

		  // Initially set to PLACED

//		System.out.println("cartid is " + cart.getCartId());
//		System.out.println(cart);
//		if (cart != null) {
//			System.out.println("cart is not null");
//		}
//		Orders orders = createOrders(order, cart);
//		System.out.println("create order" + orders);
////			Orders savedOrder = orderRepository.save(order); // Save Order first, making it persistent
//
//		List<OrderItem> orderItemList = createOrderItems(orders, cart);
//		System.out.println("orderItemList" + orderItemList);
//		orders.setOrderItems(new HashSet<>(orderItemList));
//		orders = orderRepository.save(orders);
//		if (orders != null) {
//			System.out.println(cart.getCartId());
//			cartService.clearBuyerCart(cart.getCartId());
//		}
////		}
//
////		catch(Exception e) {
////			throw new OrderProcessingException("failed to place the order try agian after some time");
////		}
//		return orders;
		    return savedOrder;

	}

	private List<OrderItem> createOrderItems(Orders order, Object cart) {
		System.out.println("inside the List<OrderItem>  ");
		List<OrderItem> orderedItems = new ArrayList();
//		for (CartItem cartItem : cart.getItems()) {
//			Product product = cartItem.getProduct();
//			OrderItem orderItem = new OrderItem();
//			int quantity = cartItem.getQuantity();
//			product.setStock(product.getStock() - quantity);
//			orderItem.setPrice(product.getPrice());
//			orderItem.setProduct(product);
//			orderItem.setQuantity(quantity);
//			orderItem.setOrder(orders);
//			orderedItems.add(orderItem);
//			productRepository.save(product);
//		}
		return orderedItems;
	}

	private Orders createOrders(Orders order, Customer customer) {

		Orders orders = new Orders();
		System.out.println();
		
		    orders.setBuyerId(customer.getId());
		    orders.setAddress(order.getAddress());
		    orders.setPaymentType(order.getPaymentType());
		    orders.setBuyername(order.getBuyername());
		    orders.setTotalAmount(order.getTotalAmount());
		    orders.setOrderDate(LocalDate.now());
		    orders.setDeliveryDate(LocalDate.now().plusDays(5)); // Set delivery date to 5 days after the order date
		    orders.setOrderStatus(OrderStatus.PLACED); // Initially set to PLACED

		return orders;
	}

	@Override
	public List<Orders> getBuyerOrderById(Long buyerId) {
		List<Orders> orders = orderRepository.findByBuyerId(buyerId);
		return orders;
	}

	@Override
	public void cancelorderById(Long orderId) {
		Orders order=orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Not found"));
		if(order.getOrderStatus()==OrderStatus.PLACED) {
			order.setOrderStatus(OrderStatus.CANCELLED);
			  orderRepository.save(order);
		}else {
			throw new RuntimeException("Order cannot be cancelled as it is already " + order.getOrderStatus());
		}
		
	}

	@Override
	public List<Orders> getAllRetailerOrders(long retailerId) {
		List<Orders> orders= orderItemRepository.findOrdersByRetailerId(retailerId);
	    System.out.println(orders);
	    if(!orders.isEmpty()) {
	    	System.out.println("orders have values");
	    }
		return orders;
	}

	

}
