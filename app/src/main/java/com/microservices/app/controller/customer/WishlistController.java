package com.microservices.app.controller.customer;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
	private RestTemplate rt = new RestTemplate();

	@Autowired
	private DiscoveryClient dicoveryClient;

	@GetMapping
	public ModelAndView listWishlist(HttpSession session, @SessionAttribute Long userId) {
		ModelAndView mv = new ModelAndView();
		List<ServiceInstance> instances = dicoveryClient.getInstances("customer-service");
		URI uri = instances.get(0).getUri();
		List response = rt.getForObject(uri + "/customers/{id}/wishlist", List.class, userId);
		mv.addObject("wishlist", response);
		mv.setViewName("wish");
		return mv;

	}

	@PostMapping("/delete")
	public ModelAndView deleteProduct(@RequestParam long productId, @SessionAttribute Long userId) {
		ModelAndView mv = new ModelAndView();
		List<ServiceInstance> instances = dicoveryClient.getInstances("customer-service");
		URI uri = instances.get(0).getUri();
		rt.delete(uri + "/customers/{userId}/wishlist?productId={productId}", userId, productId);
		mv.setViewName("redirect:/wishlist");
		return mv;
	}

	@PostMapping("/add")
	public ModelAndView addProduct(@RequestParam long productId, @SessionAttribute Long userId) {
		ModelAndView mv = new ModelAndView();

		List<ServiceInstance> instances = dicoveryClient.getInstances("customer-service");
		URI uri = instances.get(0).getUri();
		rt.postForObject(uri + "/customers/{userId}/wishlist?productId={productId}", null, String.class, userId,
				productId);
		mv.setViewName("redirect:/wishlist");
		return mv;
	}
}
