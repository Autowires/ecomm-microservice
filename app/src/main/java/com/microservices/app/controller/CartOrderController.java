package com.microservices.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartOrderController {
	@Autowired
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/cart")
	public ModelAndView getBuyerCart(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("cart");
		HttpSession session = request.getSession();
		List<ServiceInstance> instance = discoveryClient.getInstances("product-service");
		ServiceInstance serviceInstance1 = instance.get(0);
		String baseUrl1 = serviceInstance1.getUri().toString();
		baseUrl1 = baseUrl1 + "/cart/2";
		Object reposnse = restTemplate.getForObject(baseUrl1, Object.class);
		mv.addObject("cartItems", reposnse);
		return mv;

	}
}
