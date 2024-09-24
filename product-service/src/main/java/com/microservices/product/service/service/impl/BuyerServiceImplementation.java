package com.microservices.product.service.service.impl;

import org.springframework.stereotype.Service;

import com.microservices.product.service.entity.Buyer;
import com.microservices.product.service.service.BuyerServvice;

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
