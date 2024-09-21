package com.microservices.orders.service;

import com.microservices.orders.entity.Buyer;

public interface BuyerServvice {

	Buyer getBuyerById(Long buyerId);

}
