package com.microservices.orders.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.microservices.orders.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Orders {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private LocalDate orderDate; // Set default to current date
    private LocalDate deliveryDate ; // Default to 5 days after order date
	private BigDecimal totalAmount;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderItem> orderItems = new HashSet<>();
	private Long buyerId;
	private String address;
	private String paymentType;
}
