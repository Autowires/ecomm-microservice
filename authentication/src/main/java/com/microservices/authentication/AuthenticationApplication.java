package com.microservices.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import com.microservices.authentication.dto.OrderMessage;
import com.microservices.authentication.service.EmailService;


@SpringBootApplication

public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}
	@Autowired
	private EmailService emailService;
	@KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderMessage orderMessage) {
		//  admin's email
	    String adminEmail = "vishnukumpati@gmail.com";

	    // Sending email to customer
	    String customerEmail = orderMessage.getCustomerEmail();
	    String customerSubject = "Order Confirmation";
	    String customerBody = "Dear Customer, your order with ID " + orderMessage.getOrderId() + " has been placed successfully.";
	    emailService.sendEmail(customerEmail, customerSubject, customerBody);
	    System.out.println("mail sent from main of authentication");

	    // Sending email to admin
	    String adminSubject = "New Order Placed";
	    String adminBody = "A new order has been placed with ID " + orderMessage.getOrderId() + " by customer " + customerEmail;
	    emailService.sendEmail(adminEmail, adminSubject, adminBody);
	    System.out.println(" admin got the mail sent from main of authentication");

	    
	    
        System.out.println(orderMessage.getCustomerEmail()+"Received Notification for Order - {}"+ orderMessage.getOrderId());
    }


}
