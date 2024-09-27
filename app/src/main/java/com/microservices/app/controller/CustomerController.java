package com.microservices.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
	
	@GetMapping("/wishlist") // http://localhost:8080/wishlist
	public ModelAndView listWishlist()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wish");
		
		RestTemplate rt = new RestTemplate();
		
		List response = rt.getForObject("http://localhost:8084/wishlist/2", List.class);
		mv.addObject("wishlist",response);
		return mv;
	}
	
	
}
