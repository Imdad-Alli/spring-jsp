package org.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.dto.Product;
import org.jsp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private EntityManager entityManager;
	
	public boolean saveProduct(Product product, Integer id) {
		try {
			User user = entityManager.find(User.class, id);
			product.setUser(user);
			
			entityManager.persist(product);
			
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();
			
			return true;
		}
		catch(Exception e) {
			return false;			
		}
	}
	
	public List<Product> findProducts(int id){
		String jpql = "select p from Product p where p.user.id=?1";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, id);
		try {
			List<Product> products = query.getResultList();
			return products;
		} 
		catch(Exception e) {
			return null;
		}
	}

	public List<Product> findProducts(Long phone, String password){
		String jpql = "select p from Product p where p.user.phone=?1 and p.user.password=?2";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, phone);
		query.setParameter(2, password);
		
		try {
			List<Product> products = query.getResultList();
			return products;
		} 
		catch(Exception e) {
			return null;
		}
	}
	
	public List<Product> findProducts(String brand){
		String jpql = "select p from Product p where p.brand=?1";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, brand);
		try {
			List<Product> products = query.getResultList();
			return products;
		} 
		catch(Exception e) {
			return null;
		}
	}

	public List<Product> findProductsByCategory(String category){
		String jpql = "select p from Product p where p.category=?1";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, category);
		try {
			List<Product> products = query.getResultList();
			return products;
		} 
		catch(Exception e) {
			return null;
		}
	}
	
}
