package com.microservices.app.controller.customer;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

	private RestTemplate rt = new RestTemplate();

	@Autowired
	private DiscoveryClient dicoveryClient;

	@GetMapping
	public ModelAndView listReview() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/reviews");
		List<ServiceInstance> instances = dicoveryClient.getInstances("customer-service");
		URI uri = instances.get(0).getUri();
		List response = rt.getForObject(uri + "/customers/{id}/reviews", List.class, 2);
		mv.addObject("reviews", response);
		return mv;
	}

	@PostMapping("/delete")
	public ModelAndView deleteReview(@RequestParam long reviewId) {
		ModelAndView mv = new ModelAndView();
		List<ServiceInstance> instances = dicoveryClient.getInstances("customer-service");
		URI uri = instances.get(0).getUri();
		rt.delete(uri + "/customers/{userId}/reviews/{reviewId}", 2, reviewId);
		mv.setViewName("redirect:/reviews");
		return mv;
	}

	@PostMapping("/add")
	public ModelAndView addReview(@RequestParam long productId, @RequestParam String reviewContent,
			@RequestParam int rating) {
		ModelAndView mv = new ModelAndView();
		List<ServiceInstance> instances = dicoveryClient.getInstances("customer-service");
		URI uri = instances.get(0).getUri();
		Map<String, Object> request = new HashMap<>();
		request.put("productId", productId);
		request.put("reviewContent", reviewContent);
		request.put("rating", rating);
		rt.postForObject(uri + "/customers/{userId}/reviews", request, String.class, 2);
		mv.setViewName("redirect:/reviews");
		return mv;
	}

}
