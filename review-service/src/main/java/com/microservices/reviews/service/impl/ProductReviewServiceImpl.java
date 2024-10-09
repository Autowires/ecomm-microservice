package com.microservices.reviews.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.microservices.reviews.dto.ProductReviewResponse;
import com.microservices.reviews.entity.Customer;
import com.microservices.reviews.entity.Product;
import com.microservices.reviews.entity.ProductReview;
import com.microservices.reviews.repo.CustomerRepository;
import com.microservices.reviews.repo.ProductRepository;
import com.microservices.reviews.repo.ProductReviewRepository;
import com.microservices.reviews.service.ProductReviewService;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Autowired
	private ProductReviewRepository productReviewRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate = new RestTemplate();

	private Logger logger = LogManager.getLogger();

	@Override
	public List<ProductReviewResponse> getReviewsByCustomerId(Long userId) {
		return productReviewRepository.findReviewsByCustomerId(userId);
	}

	@Override
	public void saveProductReview(Long userId, long productId, String reviewContent, int rating) {

		// customer
		var customer = customerRepository.findById(userId).orElseGet(() -> {
			var path = getServiceUri("authentication") + "/customers/" + userId;
			// synchronous communication
			var cus = restTemplate.getForObject(path, Customer.class);
			return customerRepository.save(cus);
		});

		// product
		var product = productRepository.findById(productId).orElseGet(() -> {
			var path = getServiceUri("product-service") + "/products/" + productId;
			var cus = restTemplate.getForObject(path, Map.class);
			if (cus == null)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
			logger.info("Product from service " + cus);
			Product pro = new Product();
			pro.setId((Integer) cus.get("id"));
			pro.setTitle((String) cus.get("title"));
			return productRepository.save(pro);
		});

		var review = new ProductReview();
		review.setCustomer(customer);
		review.setProduct(product);
		review.setRating(rating);
		review.setReviewContent(reviewContent);

		// saving review into database
		productReviewRepository.save(review);

	}

	@Override
	public void deleteReview(long reviewId) {
		productReviewRepository.deleteById(reviewId);
	}

	@Override
	public List<ProductReview> getProductReviews(long productId) {
		return productReviewRepository.findByProductId(productId);
	}

	private String getServiceUri(String serviceName) {
		List<ServiceInstance> instance = discoveryClient.getInstances(serviceName);
		ServiceInstance serviceInstance1 = instance.get(0);
		return serviceInstance1.getUri().toString();
	}

}
