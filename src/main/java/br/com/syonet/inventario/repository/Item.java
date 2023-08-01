package br.com.syonet.inventario.repository;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Item")
@Table(name = "syo_item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String nome;

	@Column
	private String descricao;

	@Column
	private int quantidade;

	@ManyToOne
	@JoinColumn(name = "id_personagem", referencedColumnName = "id")
	private Personagem personagem;

	@Column(name = "dt_inclusao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataInc = LocalDateTime.now();

	public Item() {
	}

	public Item(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataInc() {
		return dataInc;
	}

	public void setDataInc(LocalDateTime dataInc) {
		this.dataInc = dataInc;
	}
}
