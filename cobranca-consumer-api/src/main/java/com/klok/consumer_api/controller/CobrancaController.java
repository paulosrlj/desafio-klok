package com.klok.consumer_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klok.consumer_api.domain.exception.CobrancaJaPagaException;
import com.klok.consumer_api.domain.exception.EntidadeNaoEncontradaException;
import com.klok.consumer_api.domain.model.Cobranca;
import com.klok.consumer_api.domain.service.CobrancaService;

import io.swagger.annotations.Api;



@RestController
@RequestMapping("cobrancas")
@Api(tags = "Cobranças", description = "Buscar uma cobrança ou realizar o pagamento dela")
public class CobrancaController {
	
	private CobrancaService cobrancaService;
	
	
	public CobrancaController(CobrancaService cobrancaService) {
		this.cobrancaService = cobrancaService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cobranca> buscar(@PathVariable Long id) {
		try {
			Cobranca cobrancaBuscada = cobrancaService.buscar(id);
			return ResponseEntity.ok(cobrancaBuscada);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/pagamento/{id}")
	public ResponseEntity<?> realizarPagamento(@PathVariable Long id) {
		try {
			Cobranca cobrancaBuscada = cobrancaService.buscar(id);
			
			cobrancaService.pagarCobranca(cobrancaBuscada);
			
			return ResponseEntity.ok(cobrancaBuscada);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (CobrancaJaPagaException e) {
			return ResponseEntity.ok(e.getMessage());
		}

	}
}
