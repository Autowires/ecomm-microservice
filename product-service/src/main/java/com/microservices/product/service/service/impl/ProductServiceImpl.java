package com.microservices.product.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.ProductDao;
import com.microservices.product.service.dto.ProductForm;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;

	@Override
	public List<Product> getProducts(String category, String search) {
		return dao.getProducts(category, search);

	}

	@Override
	public String addProduct(long retailerId, ProductForm form) {
		return dao.addProduct(retailerId, form);
	}

	@Override
	public String updateProduct(long retailerId, long productId, ProductForm form) {

		return dao.updateProduct(retailerId, productId, form);
	}

	@Override
	public String deleteProduct(long retailerId, long productId) {

		return dao.deleteProduct(retailerId, productId);
	}

}
