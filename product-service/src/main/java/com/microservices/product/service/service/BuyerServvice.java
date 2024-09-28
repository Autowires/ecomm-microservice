package com.microservices.product.service.service;

import com.microservices.product.service.entity.Customer;

public interface BuyerServvice {

	Customer getBuyerById(Long buyerId);

}
