package com.microservices.product.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.CustomerRepository;
import com.microservices.product.service.entity.Customer;
import com.microservices.product.service.service.BuyerServvice;

@Service
public class BuyerServiceImplementation implements BuyerServvice
{  @Autowired
   private CustomerRepository customerRepository;
	@Override
	public Customer getBuyerById(Long buyerId) {
		Customer customer = customerRepository.findById(buyerId).get();
		return customer;
	
	}
	
	


	
}