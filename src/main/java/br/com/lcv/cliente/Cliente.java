package br.com.lcv.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private int saldo;

	public Cliente() {
		
	}
	
	public Cliente(Long id, String nome, int saldo) {
		this.id = id;
		this.nome = nome;
		this.saldo = saldo;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

}
