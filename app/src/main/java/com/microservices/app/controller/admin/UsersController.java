package com.microservices.app.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.app.dto.UserStatus;
import com.microservices.app.service.AdminService;

@Controller
public class UsersController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private AdminService admin;

	@GetMapping("/admin")
	public ModelAndView getUserAndView(@RequestParam(required = false) String role) {
		ModelAndView mView = new ModelAndView("admin/users");
		if (role == null || role.isBlank()) {
			mView.addObject("users", admin.getUsers());
		} else {
			mView.addObject("users",
					admin.getUsers().stream().filter(u -> u.getOrDefault("userType", "").equals(role)).toList());
		}

		return mView;
	}

	@GetMapping("/admin/reviewRequest")
	public String reviewRequests(Model model) {
		List<Object> retailersUnderReview = admin.retailerUnderReview();
		if (retailersUnderReview == null || retailersUnderReview.isEmpty()) {
			return "admin/reviewRequest";
		}
		model.addAttribute("users", retailersUnderReview);
		return "admin/reviewRequest";
	}

	@PutMapping("/admin/status")
	public ModelAndView putUserStatus(@RequestParam long userId, @RequestParam UserStatus status,
			@RequestHeader String referer) {
		try {
			admin.changeStatus(userId, status);
		} catch (Exception e) {
			// Handle the error gracefully
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("message", "Failed to update user status: " + e.getMessage());
			return mv;
		}

		System.out.println("Referer: " + referer);

		if (referer != null && referer.contains("/admin/reviewRequest")) {
			return new ModelAndView("redirect:/admin/reviewRequest"); // Redirect to review requests page
		}

		return new ModelAndView("redirect:/admin");
	}

}
