package com.microservices.orders.service;

import org.springframework.stereotype.Service;

import com.microservices.orders.entity.Buyer;

@Service
public class BuyerServiceImplementation implements BuyerServvice
{

	@Override
	public Buyer getBuyerById(Long buyerId) {
		Buyer buyer=null;
		if(buyerId==1) {
			 buyer=new Buyer();
			 buyer.setBuyerId(1L);
			 buyer.setBuyerName("sagar");
		}
		if(buyerId==2) {
			 buyer=new Buyer();
			 buyer.setBuyerId(2L);
			 buyer.setBuyerName("sagar");
		}
	return buyer;
	}

}
