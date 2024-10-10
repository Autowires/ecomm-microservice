package com.microservices.product.service.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.OrderItemRepository;
import com.microservices.product.service.dao.OrdersRepository;
import com.microservices.product.service.dao.ProductRepository;
import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.dto.CheckoutRequest;
import com.microservices.product.service.entity.Customer;
import com.microservices.product.service.entity.OrderItem;
import com.microservices.product.service.entity.OrderStatus;
import com.microservices.product.service.entity.Orders;
import com.microservices.product.service.exception.OutOfStockException;
import com.microservices.product.service.exception.ProductNotFoundException;
import com.microservices.product.service.exception.ResourceNotFoundException;
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

	private Logger logger = LogManager.getLogger();

	@Transactional
	@Override
	public Orders placeOrder(CheckoutRequest request, Long buyerId) throws OutOfStockException {
		// TODO Auto-generated method stub

		List<CartResponse> cartItems = cartItemService.getBuyerCartById(buyerId);
		Customer customer = buyerServvice.getBuyerById(buyerId);

		double totalAmount = cartItems.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();

		Orders orders = createOrders(request, customer, totalAmount);

		Set<OrderItem> orderItems = new HashSet<>();
		for (CartResponse cartItem : cartItems) {
			var product = productRepository.findById(cartItem.getProductId())
					.orElseThrow(() -> new ProductNotFoundException());

			 if (product.getStock() <= cartItem.getQuantity()) {
				throw new  OutOfStockException(product.getId());

			}
			product.setStock(product.getStock() - cartItem.getQuantity());
			productRepository.save(product);
			OrderItem orderItem = new OrderItem(orders, product, cartItem.getQuantity(), cartItem.getPrice());
			orderItem.setOrderStatus(OrderStatus.PLACED);

			if (orderItem != null) {
				logger.debug("orderItem");
			}

			orderItems.add(orderItem);
		}
		orders.setOrderItems(orderItems);
		Orders savedOrder = orderRepository.save(orders);

		// Optionally: Clear the buyer's cart after placing the order
		if (savedOrder != null) {
			cartItemService.clearBuyerCart(buyerId);
		}

		return savedOrder;

	}

	private Orders createOrders(CheckoutRequest request, Customer customer, double totalAmount) {
		Orders orders = new Orders();
		System.out.println(request);

		orders.setBuyerId(customer.getId());
		orders.setAddress(request.getAddress());
		if(request.getPaymentType()==null) {
			orders.setPaymentType("razorpay");
		}
		orders.setPaymentType(request.getPaymentType());
		orders.setBuyername(request.getName());
		orders.setTotalAmount(totalAmount);
		orders.setOrderDate(LocalDate.now());
		return orders;
	}

	@Override
	public List<Orders> getBuyerOrderById(Long buyerId) {
		List<Orders> orders = orderRepository.findByBuyerId(buyerId);
		return orders;
	}

	@Override
	public void cancelorderItemById(Long orderItemId) {
		OrderItem order = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new RuntimeException("OrderItem Not found"));
		if (order.getOrderStatus() == OrderStatus.PLACED) {
			order.setOrderStatus(OrderStatus.CANCELLED);
			orderItemRepository.save(order);
		} else {
			throw new RuntimeException("Order cannot be cancelled as it is already " + order.getOrderStatus());
		}

	}

	@Override
	public List<Orders> getAllRetailerOrders(long retailerId) {
		List<Orders> orders = orderItemRepository.findOrdersByRetailerId(retailerId);
		System.out.println(orders);
		if (!orders.isEmpty()) {
			logger.debug("orders have values");
		}
		return orders;
	}

	@Override
	public void updateItemStatus(Long orderItemId, OrderStatus status) {
		OrderItem order = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new RuntimeException("OrderItem Not found"));
		if (status == OrderStatus.PROCESSING)
			throw new RuntimeException("Unsupported status: " + status);
		order.setOrderStatus(status);
		orderItemRepository.save(order);
	}

	@Override
	public Optional<Orders> getOrderById(long orderId) {
		return orderRepository.findById(orderId);
	}

}
