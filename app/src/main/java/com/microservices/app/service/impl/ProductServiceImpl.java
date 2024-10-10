package com.microservices.app.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.microservices.app.dto.Product;
import com.microservices.app.dto.ProductForm;
import com.microservices.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final DiscoveryClient discoveryClient;
	// private final Logger logger;
	private final RestTemplate template;

	public ProductServiceImpl(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
		// logger = LogManager.getLogger();
		template = new RestTemplate();
	}

	private URI getUri() {
		List<ServiceInstance> instances = discoveryClient.getInstances("product-service");

		if (instances == null || instances.isEmpty()) {
			throw new IllegalStateException("No instances of product-service available");
		}

		ServiceInstance serviceInstance = instances.get(0);
		return serviceInstance.getUri();
	}

	@Override
	public List<Product> getProducts(String category, String search) {
		var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products").queryParam("category", category)
				.queryParam("search", search).encode().toUriString();

		return template.getForObject(uri, List.class);

	}

	@Override
	public List<String> getAllCategories() {
		var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products/categories").build().toUriString();
		return template.getForObject(uri, List.class);
	}

	@Override
	public List<Object> recentProducts() {
		var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products/recentAdds").build().toUriString();
		return template.getForObject(uri, List.class);
	}

	@Override
	public Object getproduct(int pid) {
		var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products/" + pid).build().toUriString();
		return template.getForObject(uri, Object.class);
	}

	@Override
	public List<Object> getAllProducts(Long retailerId) {
		String uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products/getAllProducts/" + retailerId).build()
				.toUriString();
		System.out.println(uri);

		return template.getForObject(uri, List.class);

	}

	@Override
	public String updateProduct(long retailerId, long productId, ProductForm form) {
		String uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products/updateProduct/{retailerId}/{productId}")
				.buildAndExpand(retailerId, productId).toUriString();
		System.out.println(uri);
		ResponseEntity<String> response = template.exchange(uri, HttpMethod.PUT, new HttpEntity<>(form), String.class);

		return response.getBody();
	}

	@Override
	public String deleteProduct(long retailerId, long productId) {
		String uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products/deleteProduct/{retailerId}/{productId}")
				.buildAndExpand(retailerId, productId).toUriString();
		System.out.println(uri);
		ResponseEntity<String> response = template.exchange(uri, HttpMethod.DELETE, null, String.class);

		return response.getBody();
	}

	@Override
	public String addProduct(ProductForm form, long retailerId) {
		String uri = getUri() + "/products//addproduct/{retailerId}";
		return template.postForObject(uri, form, String.class, retailerId);
	}

	@Override
	public Object getRetailerproduct(long productId) {
		String uri = getUri() + "/products/getproductbyid/{productId}";
		return template.getForObject(uri, Object.class, productId);
	}

	@Override
	public List<Object> getRetailerOrders(long id) {
		String uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/orders/retailerorders/{retailerId}")
				.buildAndExpand(id).toUriString();

		System.out.println(uri); // Debugging line to check the constructed URI

		return template.getForObject(uri, List.class, id);
	}

	@Override
	public Object getProductReviews(int productId) {
		List<ServiceInstance> instances = discoveryClient.getInstances("review-service");

		if (instances == null || instances.isEmpty()) {
			throw new IllegalStateException("No instances of review service available");
		}

		ServiceInstance serviceInstance = instances.get(0);
		String uri = serviceInstance.getUri() + "/reviews?productId=" + productId;
		return template.getForObject(uri, Object.class, productId);
	}

}
