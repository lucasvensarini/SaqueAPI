package br.com.lcv.erro;

public class NaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
}
