package br.com.lcv.erro;

import org.springframework.http.HttpStatus;

public class Erro {
	
	private HttpStatus httpStatus;
	private String mensagem;
	
	public Erro(HttpStatus httpStatus, String mensagem) {
		this.httpStatus = httpStatus;
		this.mensagem = mensagem;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public String getMensagem() {
		return mensagem;
	}

}
