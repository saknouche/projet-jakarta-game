package fr.sadev.app.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.sadev.app.beans.Game;
import fr.sadev.app.beans.User;

public class GameRepository extends EntityRepository<Game, Integer> {

	public GameRepository(EntityManagerFactory emf, Class<Game> clazz) {
		super(emf, clazz);
	}
	
	
	public Game getGameByName(String name) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Game game = new Game();
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Game> tq = em.createQuery("SELECT g FROM Game g WHERE g.name = :name", Game.class);
			tq.setParameter("name", name);
			game = tq.getSingleResult();
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
		return game;
	}

	public Game getGameByDesc(String description) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Game game = new Game();
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Game> tq = em.createQuery("SELECT g FROM Game g WHERE g.description = :description", Game.class);
			tq.setParameter("description", description);
			game = tq.getSingleResult();
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
		return game;
	}
	
	public Game getUsersWithGameId(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Game game = new Game();
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Game> tq = em.createQuery("SELECT g FROM Game g LEFT JOIN FETCH g.users WHERE g.id = :id", Game.class);
			tq.setParameter("id", id);
			game = tq.getSingleResult();
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
		return game;
	}
}
