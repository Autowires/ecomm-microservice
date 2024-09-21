package com.microservices.orders.entity;

import java.math.BigDecimal;
import java.util.*;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cart {
@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long cartId;	

// Manage the reference from Cart to CartItems
@JsonManagedReference
@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<CartItem> items = new HashSet<>();


private Long buyerId;
public void addItem(CartItem item) {
    this.items.add(item);
    item.setCart(this);
}
public void removeItem(CartItem item) {
    this.items.remove(item);
    item.setCart(null);  
}


public void clearCart(){
    this.items.clear();  
}
}
