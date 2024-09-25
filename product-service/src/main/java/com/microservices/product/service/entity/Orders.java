package com.microservices.product.service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Orders {
//    @Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long orderId;
//	private LocalDate orderDate;
//	private double totalAmount;
//	@Enumerated(EnumType.STRING)
//	private OrderStatus orderStatus;
//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<OrderItem> orderItems = new HashSet<>();
//    private    Local
//	private Long buyerId;
//	
//	private String address;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private LocalDate orderDate; // Set default to current date
    private LocalDate deliveryDate ; // Default to 5 days after order date
	private double totalAmount;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderItem> orderItems = new HashSet<>();
	private Long buyerId;
	private String address;
	private String paymentType;
	private String Buyername;
	
}
