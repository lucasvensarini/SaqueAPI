package br.com.lcv.cliente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CalculadoraNotas {

	private final List<Integer> notasDisponiveis = Arrays.asList(100, 50, 20, 10);
	
	public List<InformacaoSaque> defineQuantidadeNotas(int valorSaque) {
		synchronized (this) {
			List<InformacaoSaque> informacoesSaque = new ArrayList<>();

			for (int i = 0; i < notasDisponiveis.size(); i++) {
				int notaCorrente = notasDisponiveis.get(i);
				int quantidadeNotas = 0;
				while (valorSaque >= notaCorrente) {
					quantidadeNotas++;
					valorSaque -= notaCorrente;
				}
				if (quantidadeNotas > 0) {
					informacoesSaque.add(new InformacaoSaque(quantidadeNotas, notaCorrente));
				}
				if (valorSaque == 0) {
					break;
				}
			}
			return informacoesSaque;
		}
	}
	
}
