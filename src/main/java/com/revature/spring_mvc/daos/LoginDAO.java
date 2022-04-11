package com.revature.spring_mvc.daos;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.spring_mvc.models.User;
import com.revature.spring_mvc.util.datasource.HibernateUtil;

@Repository
public class LoginDAO {
	public User findByUsernameAndPassword(String username, String password) {
		try {
			Session session = HibernateUtil.getSession();
			Query query = session.createQuery("from User u where u.username = :username and u.password = :password");
			
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			User user = (User) query.getSingleResult();
			return user;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	

	// TODO: Implement FindByEmail
	public User findByEmail(String email) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, email);
			return user;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	// TODO: Implement FindByUsername
	public User findByUsername(String username) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, username);
			return user;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean create(User newUser) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(newUser);
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}

	}
}



