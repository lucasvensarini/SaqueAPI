package br.com.lcv.cliente;

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

	private ClienteRepository clienteRepository;
	private CalculadoraNotas CalculadoraNotas;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository, CalculadoraNotas CalculadoraNotas) {
		this.clienteRepository = clienteRepository;
		this.CalculadoraNotas = CalculadoraNotas;
	}

	public Saque sacaValor(Long id, int valorSaque) {
		validaValorSaque(valorSaque);
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente != null) {
			if (permiteSaque(cliente, valorSaque)) {
				List<InformacaoSaque> informacoesSaque = CalculadoraNotas.defineQuantidadeNotas(valorSaque);
				atualizaSaldo(cliente, valorSaque);
				return new Saque(cliente, informacoesSaque);
			}
			throw new RequisicaoInvalidaException(String.format(MENSAGEM_SALDO_INSUFICIENTE, cliente.getSaldo()));
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
		return false;
	}

	private void atualizaSaldo(Cliente cliente, int valorSaque) {
		synchronized (this) {
			int novoSaldo = cliente.getSaldo() - valorSaque;
			cliente.setSaldo(novoSaldo);

			clienteRepository.save(cliente);
		}
	}

}
