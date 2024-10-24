package com.microservices.wishlist.util;

import com.microservices.wishlist.entity.Customer;
import com.microservices.wishlist.entity.Product;
import com.microservices.wishlist.exception.CustomerNotFoundException;
import com.microservices.wishlist.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ServiceClient {

    private final RestClient restClient;

    public ServiceClient(RestClient.Builder builder) {
        restClient = builder.build();
    }

    public Product getProduct(long productId) throws ProductNotFoundException {
        return restClient.get()
                .uri("http://product-service/products/{id}", productId)
                .retrieve().onStatus(this::status404Predicate, (request, response) -> {
                    throw new CustomerNotFoundException(productId);
                })
                .body(Product.class);
    }

    public Customer getCustomer(long customerId) throws CustomerNotFoundException {
        return restClient.get()
                .uri("http://authentication/customers/{id}", customerId)
                .retrieve().onStatus(this::status404Predicate, (request, response) -> {
                    throw new CustomerNotFoundException(customerId);
                })
                .body(Customer.class);
    }

    private boolean status404Predicate(HttpStatusCode httpStatusCode) {
        return httpStatusCode.isSameCodeAs(HttpStatus.NOT_FOUND);
    }
}
