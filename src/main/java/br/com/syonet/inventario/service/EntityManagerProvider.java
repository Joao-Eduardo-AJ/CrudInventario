package br.com.syonet.inventario.service;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	private static EntityManagerProvider instance;
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("InventarioJPA");

	public static EntityManagerProvider getInstance() {
		if (instance == null) {
			instance = new EntityManagerProvider();
		}
		return instance;
	}

	public <T> T executeTransaction(Function<EntityManager, T> function) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try {
			T result = function.apply(entityManager);
			transaction.commit();
			return result;
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	public void close() {
		factory.close();
	}
}
