package com.microservices.app.controller.retailer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;

import com.microservices.app.dto.OrderStatus;

@Controller
@RequestMapping("/retailer/orders")
public class OrdersController {

	@Autowired
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate = new RestTemplate();

	private String getProductServiceUri() {
		List<ServiceInstance> instance = discoveryClient.getInstances("product-service");
		ServiceInstance serviceInstance1 = instance.get(0);
		return serviceInstance1.getUri().toString();
	}

	@GetMapping
	public String getAllOrders(Model model, @SessionAttribute Long userId) {
		String path = getProductServiceUri() + "/orders/retailerorders/" + userId;
		var orders = restTemplate.getForObject(path, List.class);
		model.addAttribute("orders", orders);
		return "orders/retailer-orders";
	}

	@PostMapping("/update")
	public String updateStatus(Model model, @RequestParam OrderStatus status, @RequestParam long orderItemId) {
		String path = getProductServiceUri() + "/orderitems/" + orderItemId + "/updateStatus?status=" + status;
		restTemplate.put(path, null);
		return "redirect:/retailer/orders";
	}
}
