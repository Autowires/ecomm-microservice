package com.microservices.product.service.service.impl;

import com.microservices.product.service.dao.OrdersRepository;
import com.microservices.product.service.dao.ProductRepository;
import com.microservices.product.service.entity.*;
import com.microservices.product.service.service.BuyerServvice;
import com.microservices.product.service.service.CartService;
import com.microservices.product.service.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {
     @Autowired
	private OrdersRepository orderRepository;
	@Autowired
     private CartService cartService;
	@Autowired
	private BuyerServvice buyeService;
	@Autowired
	private ProductRepository productRepository;
	@Transactional
	@Override
	public Orders placeOrder(Orders order, Long buyerId) {
		// TODO Auto-generated method stub
	
		
	
			Cart cart=cartService.getBuyerCartById(buyerId);
             System.out.println("cartid is "+cart.getCartId());
			System.out.println(cart);
			if(cart!=null) {
				System.out.println("cart is not null");
			}
			Orders orders=createOrders(order,cart);
			System.out.println("create order"+orders);
//			Orders savedOrder = orderRepository.save(order); // Save Order first, making it persistent
			
			   List<OrderItem> orderItemList = createOrderItems(orders, cart);
			   System.out.println("orderItemList"+orderItemList );
			   orders.setOrderItems(new HashSet<>(orderItemList));
			    orders=orderRepository.save(orders);
		if(orders!= null) {
			System.out.println(cart.getCartId());
			cartService.clearBuyerCart(cart.getCartId());
		}
//		}
		
//		catch(Exception e) {
//			throw new OrderProcessingException("failed to place the order try agian after some time");
//		}
		return  orders;
		
	}
	
	private List<OrderItem> createOrderItems(Orders orders, Cart cart) {
		System.out.println("inside the List<OrderItem>  ");
	List <OrderItem> orderedItems=new ArrayList();
		for(CartItem cartItem:cart.getItems()) {
		Product product=cartItem.getProduct();
		OrderItem orderItem=new OrderItem();
		int quantity=cartItem.getQuantity();
		product.setStock(product.getStock()-quantity);
		orderItem.setPrice(product.getPrice());
		orderItem.setProduct(product);
		orderItem.setQuantity(quantity);
		orderItem.setOrder(orders);
		orderedItems.add(orderItem);
		productRepository.save(product);
	}
		return orderedItems;
	}
	private Orders createOrders(Orders order, Cart cart) {
		Customer customer =buyeService.getBuyerById(cart.getBuyerId());
		Orders orders=new Orders();
		orders.setBuyername(customer.getUsername());
		orders.setBuyerId(customer.getId());
		orders.setOrderDate(LocalDate.now().plusDays(7));
		orders.setDeliveryDate(LocalDate.now());
		orders.setOrderStatus(OrderStatus.PLACED);
		orders.setPaymentType(order.getPaymentType());
		orders.setAddress(order.getAddress());
		orders.setTotalAmount(order.getTotalAmount());
		return orders;
	}

	@Override
	public List<Orders> getBuyerOrderById(Long buyerId) {
	List<Orders> orders=orderRepository.findByBuyerId(buyerId);
		return orders;
	}

	

//	@Override
//	public OrderDTO convertToDto(Orders order) {
//		 return modelMapper.map(order, OrderDto.class);
//		return null;
//	}

}
