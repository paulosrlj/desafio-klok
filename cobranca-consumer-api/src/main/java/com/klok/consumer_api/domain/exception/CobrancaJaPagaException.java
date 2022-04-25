package com.klok.consumer_api.domain.exception;

public class CobrancaJaPagaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CobrancaJaPagaException (String mensagem) {
		super(mensagem);
	}
	
}
