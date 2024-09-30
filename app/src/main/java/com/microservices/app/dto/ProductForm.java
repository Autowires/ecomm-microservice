package com.microservices.app.dto;
import lombok.Data;

@Data
public class ProductForm {
	private String title;
	private String description;
	private String  category;
	private double price;
	private Integer stock;
	private String imageUrl;

}

