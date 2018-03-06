package br.com.lcv.erro;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class ErroController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    @ResponseBody
    public ResponseEntity<Erro> handleNaoEncontradoException(HttpServletRequest request, Throwable e) {
    	Erro erro = new Erro(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RequisicaoInvalidaException.class)
    @ResponseBody
    public ResponseEntity<Erro> handleRequisicaoInvalidaException(HttpServletRequest request, Throwable e) {
    	Erro erro = new Erro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}
