package com.microservices.product.service.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.OrdersRepository;

import com.microservices.product.service.dao.ProductRepository;
import com.microservices.product.service.dto.OrderDTO;
import com.microservices.product.service.entity.Buyer;
import com.microservices.product.service.entity.Cart;
import com.microservices.product.service.entity.CartItem;
import com.microservices.product.service.entity.OrderItem;
import com.microservices.product.service.entity.OrderStatus;
import com.microservices.product.service.entity.Orders;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.exception.OrderProcessingException;
import com.microservices.product.service.service.BuyerServvice;
import com.microservices.product.service.service.CartService;
import com.microservices.product.service.service.OrderService;
import com.microservices.product.service.service.ProductService;

import jakarta.transaction.Transactional;

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
			Orders orders=createOrders(order,cart);
//			Orders savedOrder = orderRepository.save(order); // Save Order first, making it persistent
			
			   List<OrderItem> orderItemList = createOrderItems(orders, cart);
			   orders.setOrderItems(new HashSet<>(orderItemList));
			    orders=orderRepository.save(orders);
		if(orders!= null) {
			cartService.clearBuyerCart(buyerId);
		}
//		}
		
//		catch(Exception e) {
//			throw new OrderProcessingException("failed to place the order try agian after some time");
//		}
		return  orders;
		
	}
	
	private List<OrderItem> createOrderItems(Orders orders, Cart cart) {
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
		Buyer buyer=buyeService.getBuyerById(cart.getBuyerId());
		Orders orders=new Orders();
		orders.setBuyername(buyer.getBuyerName());
		orders.setBuyerId(buyer.getBuyerId());
		orders.setOrderDate(LocalDate.now().plusDays(7));
		orders.setDeliveryDate(LocalDate.now());
		orders.setOrderStatus(OrderStatus.PLACED);
		orders.setPaymentType(order.getPaymentType());
		orders.setAddress(order.getAddress());
		orders.setTotalAmount(order.getTotalAmount());
		return orders;
	}

//	@Override
//	public OrderDTO convertToDto(Orders order) {
//		 return modelMapper.map(order, OrderDto.class);
//		return null;
//	}

}
