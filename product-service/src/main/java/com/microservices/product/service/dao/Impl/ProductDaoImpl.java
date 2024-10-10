package com.microservices.product.service.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservices.product.service.dao.ProductDao;
import com.microservices.product.service.dto.ProductForm;
import com.microservices.product.service.dto.ProductInfoResponse;
import com.microservices.product.service.entity.Product;

import jakarta.persistence.criteria.Predicate;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getProducts(String category, String search) {
		return sessionFactory.fromSession(session -> {
			var builder = session.getCriteriaBuilder();
			var query = builder.createQuery(Product.class);
			var root = query.from(Product.class);

			// Build predicates based on the provided criteria
			var predicates = new ArrayList<Predicate>();

			if (search != null && !search.isBlank()) {
				var searchPattern = "%" + search + "%";
				var searchPredicate = builder.like(root.get("title"), searchPattern);
				predicates.add(searchPredicate);
			}

			if (category != null && !category.isBlank()) {
				var categoryPredicate = builder.equal(root.get("category"), category);
				predicates.add(categoryPredicate);
			}

			query.where(predicates.toArray(new Predicate[0]));
			System.out.println(predicates);

			return session.createQuery(query).getResultList();
		});
	}

	@Override
	public String addProduct(long retailerId, ProductForm form) {
		Product product = new Product();
		product.setTitle(form.getTitle());
		product.setDescription(form.getDescription());
		product.setPrice(form.getPrice());
		product.setCategory(form.getCategory());
		product.setStock(form.getStock());
		product.setImageUrl(form.getImageUrl());

		sessionFactory.inTransaction(session -> {
			product.setRetailerId(retailerId);
			session.persist(product);
		});
		return "Product is Listed for Sale. We'll let u know when an Order is placed";
	}

	@Override
	public String updateProduct(long retailerId, Long productId, ProductForm form) {
		Product product = new Product();
		product.setTitle(form.getTitle());
		product.setDescription(form.getDescription());
		product.setPrice(form.getPrice());
		product.setCategory(form.getCategory());
		product.setStock(form.getStock());
		product.setImageUrl(form.getImageUrl());
		product.setId(productId);

		return sessionFactory.fromTransaction(session -> {
			product.setRetailerId(retailerId);
			session.merge(product);
			return session.merge(product) != null ? "updated" : "notUpdated";
		});
	}

	@Override
	public String deleteProduct(long retailerId, Long productId) {
		return sessionFactory.fromTransaction(session -> {
			Product product = session.get(Product.class, productId);
			if (product != null && product.getRetailerId() == retailerId) {
				session.remove(product);
				return "Deleted Successfully";
			} else {
				return "Product not found";
			}
		});
	}

	@Override
	public Product findProductById(Long productId) {
		Product product = null;
		Session session = sessionFactory.openSession();
		System.out.println("inside the findProductById");
		Query<Product> query = session.createQuery("from Product p where p.id=:productId", Product.class);
		query.setParameter("productId", productId);
		product = query.uniqueResult();
		System.out.println(product);
		return product;
	}

	@Override
	public List<String> getCategories() {
		Session session = null;
		List<String> categories = null;
		try {
			session = sessionFactory.openSession();
			Query<String> query = session.createQuery("select distinct p.category from Product p", String.class);
			categories = query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return categories;
	}

	@Override
	public List<Product> recentAdds() {
		Session session = null;
		List<Product> products = null;
		try {
			session = sessionFactory.openSession();
			Query<Product> query = session.createQuery("from Product p order by p.registeredAt DESC", Product.class);
			query.setMaxResults(6);

			products = query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return products;
	}

	@Override
	public ProductInfoResponse getProductInfo(long productId) {
		return sessionFactory.fromSession(session -> {
			Product product = session.get(Product.class, productId);
			if (product == null) {
				return null; // Handle case where the product doesn't exist
			}
			var responInfoResponse = new ProductInfoResponse(product);
			return responInfoResponse;
		});
	}

	@Override
	public List<Product> getAllProducts(Long retailerId) {
		Session session = sessionFactory.openSession();
		Query<Product> query = session.createQuery("from Product where retailerId = :retailerId", Product.class);
		query.setParameter("retailerId", retailerId);
		List<Product> products = query.getResultList();
		session.close();
		return products;
	}

}
