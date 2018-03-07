package br.com.lcv.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/clientes", produces = "application/json")
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping(path = "/{id}/saque/{valorSaque}")
	public ResponseEntity<Saque> saca(@PathVariable Long id, @PathVariable int valorSaque) {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.sacaValor(id, valorSaque));
	}

}
