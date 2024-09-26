package com.microservices.app.service.impl;

import java.net.URI;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.microservices.app.dto.Product;
import com.microservices.app.service.ProductService;

import jakarta.ws.rs.core.UriBuilder;

@Service
public class ProductServiceImpl implements ProductService {

    private final DiscoveryClient discoveryClient;
    //private final Logger logger;
    private final RestTemplate template;

    public ProductServiceImpl(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        //logger = LogManager.getLogger();
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
        var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/products")
                .queryParam("category", category)
                .queryParam("search", search)
                .encode()
                .toUriString();
        
        return template.getForObject(uri, List.class);
        
        
    }

	@Override
	public List<String> getAllCategories() {
		var uri=UriComponentsBuilder.fromHttpUrl(getUri()+"/products/categories")
				                                                   .build()
				                                                   .toUriString();
		return template.getForObject(uri,List.class);
	}
	
	@Override
	public List<String> recentProducts(){
		var uri=UriComponentsBuilder.fromHttpUrl(getUri()+"/products/recentAdds")
				                      .build()
				                      .toUriString();
		return template.getForObject(uri,List.class);
	}
}
