package br.com.lcv.cliente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lcv.erro.NaoEncontradoException;
import br.com.lcv.erro.RequisicaoInvalidaException;

@Service
public class ClienteService {

	private static final String MENSAGEM_VALOR_INVALIDO = "O valor deve ser positivo";
	private static final String MENSAGEM_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
	private static final String MENSAGEM_SALDO_INSUFICIENTE = "Saldo insuficiente. Saldo = %d";
	
	private final List<Integer> notasDisponiveis = Arrays.asList(100, 50, 20, 10);

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Saque sacaValor(Long id, int valorSaque) {
		validaValorSaque(valorSaque);
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente != null) {
			if (permiteSaque(cliente, valorSaque)) {
				List<InformacaoSaque> informacoesSaque = sacaValor(valorSaque);
				atualizaSaldo(cliente, valorSaque);
				return new Saque(cliente, informacoesSaque);
			}
		}
		throw new NaoEncontradoException(MENSAGEM_CLIENTE_NAO_ENCONTRADO);
	}
	
	private void validaValorSaque(int valorSaque) {
		if (valorSaque <= 0) {
			throw new RequisicaoInvalidaException(MENSAGEM_VALOR_INVALIDO);
		}
	}
	
	private boolean permiteSaque(Cliente cliente, int valorSaque) {
		if (cliente.getSaldo() >= valorSaque) {
			return true;
		}
		throw new RequisicaoInvalidaException(String.format(MENSAGEM_SALDO_INSUFICIENTE, cliente.getSaldo()));
	}

	private List<InformacaoSaque> sacaValor(int valorSaque) {
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
	
	private void atualizaSaldo(Cliente cliente, int valorSaque) {
		int novoSaldo = cliente.getSaldo() - valorSaque;
		cliente.setSaldo(novoSaldo);
		
		clienteRepository.save(cliente);
	}

}
