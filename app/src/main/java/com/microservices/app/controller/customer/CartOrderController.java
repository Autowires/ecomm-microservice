package com.microservices.app.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartOrderController {
	@Autowired
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate = new RestTemplate();

	private String getProductServiceUri() {
		List<ServiceInstance> instance = discoveryClient.getInstances("product-service");
		ServiceInstance serviceInstance1 = instance.get(0);
		return serviceInstance1.getUri().toString();
	}

	@GetMapping("/cart")
	public ModelAndView getBuyerCart() {
		ModelAndView mv = new ModelAndView("cart");
		String baseUrl1 = getProductServiceUri() + "/customers/{ui}/cart";
		Object reposnse = restTemplate.getForObject(baseUrl1, Object.class, 1);
		mv.addObject("cartItems", reposnse);
		return mv;
	}

	@PostMapping("/cart")
	public String addToCart(@RequestParam long productId) {
		String baseUrl1 = getProductServiceUri() + "/customers/{ui}/cart?productId={pi}";
		restTemplate.postForObject(baseUrl1, null, String.class, 1, productId);
		return "redirect:/cart";
	}

	@PutMapping("/cart")
	public String updateCartItemQuantity(@RequestParam long cartItemId, @RequestParam long productId,
			@RequestParam int quantity) {
		String path = getProductServiceUri() + "/customers/{ui}/cart/{ci}?productId={pi}&quantity={q}";
		restTemplate.put(path, null, 1, cartItemId, productId, quantity);
		return "redirect:/cart";
	}

	@DeleteMapping("/cart")
	public String deleteCartItem(@RequestParam long cartItemId) {
		String path = getProductServiceUri() + "/customers/{ui}/cart/{ci}";
		restTemplate.delete(path, 1, cartItemId);
		return "redirect:/cart";
	}

}
