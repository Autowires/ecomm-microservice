package com.microservices.product.service.dao.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservices.product.service.dao.ProductReviewDAO;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.entity.ProductReview;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductReviewImpl implements ProductReviewDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ProductReview> findByProductId(long productId) {
		return sessionFactory.fromSession(session -> {
			return session.createQuery("FROM ProductReview where product.id= :productId", ProductReview.class)
					.setParameter("productId", productId).getResultList();
		});
	}

	@Override
	public List<ProductReview> getCustomerReviews(long userId) {
		return sessionFactory.fromSession(session -> {
			return session.createQuery("from ProductReview pr where pr.customer.id = :cid", ProductReview.class)
					.setParameter("cid", userId).getResultList();
		});
	}

	@Override
	public void addCustomerReview(long userId, long productId, String reviewContent, int rating) {
		sessionFactory.inTransaction(session -> {
			var product = session.get(Product.class, productId);
			ProductReview review = new ProductReview();
			review.setCustomerId(userId);
			review.setProduct(product);
			review.setRating(rating);
			review.setReviewContent(reviewContent);
			session.persist(review);
		});
	}

	@Override
	public void deleteReview(long id) {
		sessionFactory.inTransaction(session -> {
			session.createQuery("delete from ProductReview pr where pr.id = :id").setParameter("id", id)
					.executeUpdate();
		});
	}

}