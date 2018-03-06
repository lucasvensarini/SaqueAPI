package br.com.lcv.cliente;

import java.util.List;

public class Saque {
	
	private Cliente cliente;
	private List<InformacaoSaque> informacoesSaque;
	
	public Saque(Cliente cliente, List<InformacaoSaque> informacoesSaque) {
		this.cliente = cliente;
		this.informacoesSaque = informacoesSaque;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public List<InformacaoSaque> getInformacoesSaque() {
		return informacoesSaque;
	}

}
