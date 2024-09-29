package com.microservices.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.app.dto.CartDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;


@Controller
public class CartOrderController {
      @Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/getbuyercart")
	public ModelAndView getBuyerCart(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		HttpSession session = request.getSession();
	    List<ServiceInstance> instance=discoveryClient.getInstances("PRODUCT-SERVICE");
		ServiceInstance serviceInstance1=instance.get(0);
		
	    String baseUrl1=serviceInstance1.getUri().toString();//return http://localhost:8083
	    baseUrl1=baseUrl1+"/cart"+"/getbuyercart"+"/1";
	    RestTemplate restTemplate=new RestTemplate();
	    CartDto  cartItems=  restTemplate.getForObject(baseUrl1, CartDto.class);
	   if(cartItems!=null) {
		   System.out.println("Not null");
	   }else {
		   System.out.println("null");
	   }
		mv.addObject("cartItems",cartItems);
		mv.setViewName("cart");
	    return mv;
		
	}
}
