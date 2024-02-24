package org.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private EntityManager entityManager;

	public boolean saveUser(User user) {
		try {
			entityManager.persist(user);

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateUser(User user) {
		User response = entityManager.find(User.class, user.getId());
		if (response != null) {
			response.setName(user.getName());
			response.setPhone(user.getPhone());
			response.setEmail(user.getEmail());
			response.setPassword(user.getPassword());

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();

			return true;
		} else {
			return false;
		}
	}

	public User verify(long phone, String password) {
		String jpql = "select u from User u where u.phone=?1 and u.password=?2";

		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, phone);
		query.setParameter(2, password);

		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public User verify(String email, String password) {
		String jpql = "select u from User u where u.email=?1 and u.password=?2";

		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, password);

		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public User findUser(int id) {
		User user = entityManager.find(User.class, id);

		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public User findUserByProductId(int id) {
		String jpql = "select p.user from Product p where p.id=?1";

		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, id);

		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}
}
