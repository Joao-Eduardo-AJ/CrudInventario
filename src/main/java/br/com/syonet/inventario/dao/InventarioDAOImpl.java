package br.com.syonet.inventario.dao;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import br.com.syonet.inventario.repository.Item;
import br.com.syonet.inventario.repository.Personagem;
import br.com.syonet.inventario.service.EntityManagerProvider;

public class InventarioDAOImpl {
	private static EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();

	public void savePersonagem(Personagem entity) {
		System.out.println("Se preparando para salvar o(a) " + entity.getNome());

		List<Item> itens = getItens();

		System.out.println("Itens disponíveis");
		itens.forEach(item -> System.out.println(item.getNome()));
		System.out.println("-----------------");

		List<Item> inventario = entity.getInventario();
		List<String> nomeItens = itens.stream().map(el -> el.getNome()).collect(Collectors.toList());

		System.out.println("Itens no inventario");
		inventario.forEach(item -> System.out.println(item.getNome()));
		System.out.println("-----------------");

		Boolean contains = inventario.stream().allMatch(item -> nomeItens.contains(item.getNome()));

		if (contains) {
			entityManagerProvider.executeTransaction(manager -> {
				manager.persist(entity);
				return null;
			});
		} else {
			System.out.println(
					"===================================================== ERRO =====================================================");
			System.out.println("Impossível salvar o personagem " + entity.getNome()
					+ " pois um de seus equipametos não consta na lista de itens.");
		}
	}

	public void saveItem(Item entity) {
		entityManagerProvider.executeTransaction(manager -> {
			manager.persist(entity);
			return null;
		});
	}

	public List<Item> getItens() {
		TypedQuery<Item> query = entityManagerProvider
				.executeTransaction(manager -> manager.createQuery("select i from Item i", Item.class));

		List<Item> itens = query.getResultList();
		return itens;
	}
}
