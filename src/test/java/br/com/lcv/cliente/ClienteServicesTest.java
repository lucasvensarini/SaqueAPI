package br.com.lcv.cliente;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteServicesTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteService service;

	@MockBean
	private ClienteRepository repository;

	private Cliente cliente;

	@Before
	public void setup() {
		cliente = new Cliente(100L, "Lucas", 500);
	}

	@Test
	public void deveRetornarSaqueComSucesso() throws Exception {
		Mockito.when(service.sacaValor(100L, 20)).thenReturn(new Saque(cliente, new ArrayList()));
		Mockito.when(repository.findOne(100L)).thenReturn(cliente);

		mockMvc.perform(MockMvcRequestBuilders.get("/clientes/100/saque/20")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(service, Mockito.timeout(1)).sacaValor(100L, 20);
	}
	
}
