package br.com.syonet.inventario;

import java.util.List;

import br.com.syonet.inventario.dao.InventarioDAOImpl;
import br.com.syonet.inventario.repository.Classe;
import br.com.syonet.inventario.repository.Item;
import br.com.syonet.inventario.repository.Personagem;

public class Application {
	
	private static InventarioDAOImpl crud = new InventarioDAOImpl();
	
	public static void main(String args[]) {
		Item espada = new Item("espada", "aspecto flamejante, afiação III, durabilidade II");
		Item picareta = new Item("picareta", "eficiencia III, remendo");
		Item pa = new Item("pa", "eficiencia V, durabilidade IV");
		Item enxada = new Item("enxada", "durabilidade IV, remendo");
		Item arco = new Item("arco", "infinidade, empurrao II, durabilidade IV");
		
		List<Item> itens = List.of(espada, picareta, pa, enxada, arco);
		itens.forEach(item -> {
			crud.saveItem(item);
		});
		
		Personagem steve = new Personagem("Steve", Classe.GUERREIRO);
		List<Item> itensSteve = List.of(espada, enxada, arco);
		steve.setInventario(itensSteve);
		
		crud.savePersonagem(steve);

		Personagem alex = new Personagem("Alex", Classe.MAGO);
		Item cajado = new Item("cajado", "alcance III, aspecto flamejante, durabilidade IV");
		List<Item> itensAlex = List.of(cajado, picareta, pa, enxada);
		alex.setInventario(itensAlex);
		
		crud.savePersonagem(alex);
	}

}
