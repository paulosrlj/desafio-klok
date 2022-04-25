package com.klok.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klok.api.domain.exception.EntidadeEmUsoException;
import com.klok.api.domain.exception.EntidadeNaoEncontradaException;
import com.klok.api.domain.model.Adesao;
import com.klok.api.domain.service.AdesaoService;
import com.klok.api.util.NullAwareBeanUtilsBean;

@RestController
@RequestMapping("adesoes")
public class AdesaoController {

	AdesaoService adesaoService;

	public AdesaoController(AdesaoService adesaoService) {
		this.adesaoService = adesaoService;
	}

	@PostMapping
	public ResponseEntity<Adesao> cadastrar(@RequestBody Adesao adesao) {
		return ResponseEntity.status(HttpStatus.CREATED).body(adesaoService.cadastrar(adesao));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Adesao> buscar(@PathVariable Long id) {
		try {
			Adesao adesaoBuscada = adesaoService.buscar(id);
			return ResponseEntity.ok(adesaoBuscada);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Adesao> cancelar(@PathVariable Long id) {
		try {
			adesaoService.cancelar(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Adesao> atualizar(@PathVariable Long id, @RequestBody Adesao adesao) {
		Adesao adesaoBuscada = adesaoService.buscar(id);

		if (adesaoBuscada != null) {

			try {
				NullAwareBeanUtilsBean.copyDiff(adesaoBuscada, adesao);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				e.printStackTrace();
			}

			adesaoService.atualizar(adesaoBuscada);

			return ResponseEntity.ok(adesaoBuscada);
		}

		return ResponseEntity.notFound().build();
	}
}
