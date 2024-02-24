package org.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

	@Autowired
	private EntityManager entityManager;

	public boolean saveAdmin(Admin admin) {
		try {
			entityManager.persist(admin);

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean updateAdmin(Admin admin) {
		Admin response = entityManager.find(Admin.class, admin.getId());

		if (response != null) {
			response.setName(admin.getName());
			response.setPhone(admin.getPhone());
			response.setEmail(admin.getEmail());
			response.setGender(admin.getGender());
			response.setPassword(admin.getPassword());

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();

			return true;
		} else {
			return false;
		}
	}

	public boolean updateAdminByEmailAndPassword(Admin admin) {
		Admin response = verify(admin.getEmail(), admin.getPassword());
		
		if (response != null) {
			response.setName(admin.getName());
			response.setPhone(admin.getPhone());
			response.setEmail(admin.getEmail());
			response.setGender(admin.getGender());
			response.setPassword(admin.getPassword());
			
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();
			
			return true;
		} else {
			return false;
		}
	}

	public Admin verify(long phone, String password) {
		String jpql = "select a from Admin a where a.phone=?1 and a.password=?2";

		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, phone);
		query.setParameter(2, password);

		try {
			Admin admin = (Admin) query.getSingleResult();
			return admin;
		} catch (Exception e) {
			return null;
		}
	}

	public Admin verify(String email, String password) {
		String jpql = "select a from Admin a where a.email=?1 and a.password=?2";

		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, password);

		try {
			Admin admin = (Admin) query.getSingleResult();
			return admin;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Admin findAdmin(int id) {
		Admin admin = entityManager.find(Admin.class, id);

		if (admin != null) {
			return admin;
		} else {
			return null;
		}
	}
}
