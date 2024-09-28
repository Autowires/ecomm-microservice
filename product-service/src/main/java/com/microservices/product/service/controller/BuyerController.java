package com.microservices.product.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.entity.Buyer;
import com.microservices.product.service.service.BuyerServvice;
@RestController
@RequestMapping("/buyerController")
public class BuyerController {
	@Autowired
	private BuyerServvice buyerServvice;
	@GetMapping("/buyer/{buyerId}")
	public ResponseEntity<Buyer> findBuyerById(@PathVariable Long buyerId){
	Buyer buyer=buyerServvice.getBuyerById(buyerId);
	return ResponseEntity.ok(buyer);
	}

}
