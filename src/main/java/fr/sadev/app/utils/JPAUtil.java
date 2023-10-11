package fr.sadev.app.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;

	public static EntityManagerFactory getEntityManagerFactory(String persistUnit) {

		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(persistUnit);

		}
		return factory;
	}
}
