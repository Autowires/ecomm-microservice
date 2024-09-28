package com.microservices.product.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.BuyerRepository;
import com.microservices.product.service.entity.Buyer;
import com.microservices.product.service.service.BuyerServvice;

@Service
public class BuyerServiceImplementation implements BuyerServvice
{  @Autowired
   private BuyerRepository buyerRepository;
	@Override
	public Buyer getBuyerById(Long buyerId) {
		Buyer buyer=buyerRepository.findById(buyerId).get();
		return buyer;
	
	}
	
	


	
}