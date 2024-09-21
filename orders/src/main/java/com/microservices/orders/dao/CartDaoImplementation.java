package com.microservices.orders.dao;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservices.orders.entity.Buyer;
import com.microservices.orders.entity.Cart;
import com.microservices.orders.entity.CartItem;
import com.microservices.orders.entity.Product;
import com.microservices.orders.exception.ResourceNotFoundException;
import com.microservices.orders.service.CartService;

//@Repository
//public class CartDaoImplementation  implements CartDao{
    
     
//	
//	@Autowired
//	private SessionFactory sessionFactory;
//	@Override
//	public Buyer getBuyerById(Long buyerId) {
//		Buyer buyer=null;
//		try {
//			Session session = sessionFactory.openSession();
//	        Query<Buyer> query = session.createQuery("from Buyer b where b.buyerId = :buyerId", Buyer.class);
//	        query.setParameter("buyerId", buyerId);
//	        //uniqueResult()Execute the query and return the single result of the query, 
//	        //or null if the query returns no results.
//	        buyer= query.uniqueResult();
//	        
//	    } catch (Exception e) {
//	        // Log the error and throw a custom exception if needed
//	        System.err.println("Error fetching Buyer by ID: " + e.getMessage());
//	        throw new RuntimeException("Unable to BuyerDeatils", e);       
//	    }
//		return buyer;
//		
//	}
//	@Override
//	public Cart initializeNewCart(Buyer buyer) {
//		System.out.println("inside intializerNewCart");
//		System.out.println(buyer);
//		  Cart cart=null;
//		  Cart newCart=null;
//		 Session session = sessionFactory.openSession();
//		    Transaction tr = session.beginTransaction(); 
//		try {
//
//			System.out.println("the buyer id is "+buyer.getBuyerId());
//			System.out.println("query execution started");
//		  Query<Cart> query = session.createQuery("from Cart c where c.buyer.buyerId = :buyerId", Cart.class);
//	        query.setParameter("buyerId", buyer.getBuyerId());
//	        System.out.println("query execution stopped");
//	        //uniqueResult()Execute the query and return the single result of the query, 
//	        //or null if the query returns no results.
//	        System.out.println("before fetchiing cart ");
//	        cart = query.uniqueResult();
//	        System.out.println( "your cart is "+cart);
//	        System.out.println("after fetching the cart" +cart);
//	        if(cart!=null) {
//	        	System.out.println("inside cart !=null");
//	        	System.out.println(cart.getCartId());
//	        	return cart;
//	        	
//	        }else {
//	        	System.out.println("inside else block");
//	        	  newCart = new Cart();
//	        	 newCart.setBuyer(buyer);
//	            
////	            // Save the new cart
//	             session.persist(newCart);
//	             tr.commit();
//	             System.out.println("Before returning");
//	 	        System.out.println(newCart);
//	        }
//	  
//	       
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
//		
//		System.out.println(newCart);
//		return newCart;
//	
//
//		
//	}
//	
//		
//	
//	
//	
//	@Override
//	public Cart addItemTtoCart(Long cartId, Long productId, Integer quantity) {
//	Session session=sessionFactory.openSession();
//	Transaction tr=session.beginTransaction();
//	Cart cart=getCart(cartId);
//	System.out.println("+++++++++++++++++++++++++++++++++++++++++");
//	System.out.println("cart id fetching from getCart"+cart.getCartId());
//	System.out.println(cart);
//	Product product=getProductById(productId);
//	//Unchecked Exceptions: RuntimeException is an unchecked exception
//	//fail fast
//	try {
//	if(product==null) {
//		throw new ResourceNotFoundException("Product Not found with id"+productId);
//	}else {
//		  //this method check is if the product is already present in the cart. This is done by filtering through the cart.getItems() using a Stream.
//			//  It looks for an item where the product's ID matches the productId. If found, it returns that CartItem.
//			 // If no such item is found, it creates a new CartItem object using new CartItem().
//		
//		CartItem cartItem=cart.getItems()
//				.stream()
//				.filter(item->item.getProduct().getProductId().equals(productId)).findFirst().orElse(new CartItem());
//		
//		   if(cartItem==null) {
//		      cartItem.setCart(cart);
//		      cartItem.setProduct(product);
//			  cartItem.setQuantity(quantity);
//			  cartItem.setUnitPrice(product.getPrice());
//		}else {
//			cartItem.setQuantity(cartItem.getQuantity()+quantity);
//		}
//		   cartItem.setTotalPrice();
//		   cart.addItem(cartItem);
//	       session.merge(cart);
//	       tr.commit();
//	}
//	}catch(Exception e) {
//		
//	}
//		return cart;
//	}
//	
//
//	private Cart getCart(Long cartId) {
//		Session session=sessionFactory.openSession();
//		Cart cart=null;
//		
//		 Query<Cart> query=session.createQuery("from Cart c where c.cartId=:cartId",Cart.class);
//	     query.setParameter("cartId", cartId);
//		 cart=query.uniqueResult();
//		
//	if(cart==null) {
//		throw new  ResourceNotFoundException("cart is Not found for the user"+cartId);
//	}
//		else {
//      	  BigDecimal totalAmount = cart.getTotalAmount();
//      	  cart.setTotalAmount(totalAmount);
//      	  session.persist(cart);
//        }
//	session.close();
//		return cart;
//	}
//	
//	private Product getProductById(Long productId) {
//	    	
//		System.out.println("the product id is"+productId);
//		    Session session=sessionFactory.openSession();
//	     	Product product=null;
//	     
//			Query<Product> query=session.createQuery("from Product p where p.productId=:productId",Product.class);
//			
//			product=query.uniqueResult();
//			return product;
//	}

//}
