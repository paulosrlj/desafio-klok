package com.klok.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klok.api.domain.exception.EntidadeNaoEncontradaException;
import com.klok.api.domain.model.Cobranca;
import com.klok.api.domain.service.CobrancaService;
import com.klok.api.domain.service.RabbitMqService;
import com.klok.library.dto.CobrancaDto;
import com.klok.library.rabbitmq.connections.constants.RabbitMQConstants;

@RestController
@RequestMapping("cobrancas")
public class CobrancaController {

	private CobrancaService cobrancaService;

	private RabbitMqService rabbitMqService;

	public CobrancaController(CobrancaService cobrancaService, RabbitMqService rabbitMqService) {
		this.cobrancaService = cobrancaService;
		this.rabbitMqService = rabbitMqService;
	}

	@PostMapping
	public ResponseEntity<Cobranca> salvar(@RequestBody Cobranca cobranca) {
		Cobranca cobrancaCriada = cobrancaService.agendar(cobranca);

		CobrancaDto cobrancaDto = new CobrancaDto();
		BeanUtils.copyProperties(cobrancaCriada, cobrancaDto);

		this.rabbitMqService.enviaMensagem(RabbitMQConstants.FILA_COBRANCA, cobrancaDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(cobrancaCriada);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cobranca> buscar(@RequestParam Long id) {
		try {
			Cobranca cobrancaBuscada = cobrancaService.buscar(id);
			return ResponseEntity.ok(cobrancaBuscada);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
