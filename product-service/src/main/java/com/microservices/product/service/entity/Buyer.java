package com.microservices.product.service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class Buyer {
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long buyerId;
    private String buyerName;
	
	
}
