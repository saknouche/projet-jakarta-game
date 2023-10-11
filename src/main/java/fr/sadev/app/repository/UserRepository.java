package fr.sadev.app.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.sadev.app.beans.User;
import fr.sadev.app.utils.Hash;

public class UserRepository extends EntityRepository<User, Integer> {

	public UserRepository(EntityManagerFactory emf, Class<User> clazz) {
		super(emf, clazz);
		// TODO Auto-generated constructor stub
	}

	public User getUserByEmail(String email) {
		EntityManager em = null;
		EntityTransaction tx = null;
		User user = new User();
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
			tq.setParameter("email", email);
			user = tq.getSingleResult();
			tx.commit();
			em.close();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {// s'éxecute dans tout les cas (erreur ou non)
			if (em != null)
				em.close();
		}
		return user;
	}
	
	public User getUserByPassword(String password) {
		EntityManager em = null;
		EntityTransaction tx = null;
		User user = new User();
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.password = :password", User.class);
			tq.setParameter("password", password);
			user = tq.getSingleResult();
			tx.commit();
			em.close();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {// s'éxecute dans tout les cas (erreur ou non)
			if (em != null)
				em.close();
		}
		return user;
	}
	
	public User getGamesByUserId(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		User user = new User();
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<User> tq = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.games WHERE u.id = :id", User.class);
			tq.setParameter("id", id);
			user = tq.getSingleResult();
			tx.commit();
			em.close();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {// s'éxecute dans tout les cas (erreur ou non)
			if (em != null)
				em.close();
		}
		return user;
	}
}
