package com.microservices.authentication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Retailer extends MyUser{
	
	@Column(nullable = false)
	private String shopName;
	
	@Column(nullable = false)
	private String GSTIN;
	
	@Column(nullable = false)
  
	private String pannumber;
	
	@Column(nullable = false)
	private String address;

	
	
	
	

}
