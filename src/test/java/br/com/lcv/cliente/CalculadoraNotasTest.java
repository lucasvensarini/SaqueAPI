package br.com.lcv.cliente;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraNotasTest {
	
	private CalculadoraNotas CalculadoraNotas;
	
	@Before
	public void setup() {
		CalculadoraNotas = new CalculadoraNotas();
	}
	
	@Test
	public void deveCalcular280() {
		List<InformacaoSaque> informacoesSaques = CalculadoraNotas.defineQuantidadeNotas(280);
		
		Assert.assertEquals(4, informacoesSaques.size());
		Assert.assertEquals(informacoesSaques.get(0).getQuantidadeNotas(), 2);
		Assert.assertEquals(informacoesSaques.get(0).getValor(), 100);
		Assert.assertEquals(informacoesSaques.get(1).getQuantidadeNotas(), 1);
		Assert.assertEquals(informacoesSaques.get(1).getValor(), 50);
		Assert.assertEquals(informacoesSaques.get(2).getQuantidadeNotas(), 1);
		Assert.assertEquals(informacoesSaques.get(2).getValor(), 20);
		Assert.assertEquals(informacoesSaques.get(3).getQuantidadeNotas(), 1);
		Assert.assertEquals(informacoesSaques.get(3).getValor(), 10);
	}

}
