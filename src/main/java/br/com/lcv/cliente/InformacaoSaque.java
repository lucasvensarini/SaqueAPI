package br.com.lcv.cliente;

public class InformacaoSaque {

	private int quantidadeNotas;
	private int valor;

	public InformacaoSaque(int quantidadeNotas, int valor) {
		this.quantidadeNotas = quantidadeNotas;
		this.valor = valor;
	}

	public int getQuantidadeNotas() {
		return quantidadeNotas;
	}

	public int getValor() {
		return valor;
	}

}
