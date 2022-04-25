package com.klok.consumer_api.domain.service;

import org.springframework.stereotype.Service;

import com.klok.consumer_api.domain.exception.CobrancaJaPagaException;
import com.klok.consumer_api.domain.exception.EntidadeNaoEncontradaException;
import com.klok.consumer_api.domain.model.Cobranca;
import com.klok.consumer_api.domain.repository.CobrancaRepository;

@Service
public class CobrancaService {

	CobrancaRepository cobrancaRepository;

	public CobrancaService(CobrancaRepository cobrancaRepository) {
		this.cobrancaRepository = cobrancaRepository;
	}

	public Cobranca salvar(Cobranca cobranca) {
		return cobrancaRepository.salvar(cobranca);
	}

	public Cobranca buscar(Long id) {
		Cobranca cobranca = cobrancaRepository.buscar(id);
		if (cobranca == null) {
			throw new EntidadeNaoEncontradaException(String.format("A cobrança de id %d não existe.", id));
		}

		return cobranca;
	}

	public Cobranca pagarCobranca(Cobranca cobranca) {
		if (cobranca.isPago()) {
			throw new CobrancaJaPagaException(String.format("A cobrança de id %d e valor %.2f, fá foi paga.",
					cobranca.getId(), cobranca.getValor()));
		}

		cobranca.setPago(true);
		return cobrancaRepository.salvar(cobranca);
	}

}
