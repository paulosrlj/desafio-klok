package com.klok.api.domain.service;

import org.springframework.stereotype.Service;

import com.klok.api.domain.exception.EntidadeNaoEncontradaException;
import com.klok.api.domain.model.Cobranca;
import com.klok.api.domain.repository.CobrancaRepository;

@Service
public class CobrancaService {
	
	CobrancaRepository cobrancaRepository;
	
	public CobrancaService(CobrancaRepository cobrancaRepository) {
		this.cobrancaRepository = cobrancaRepository;
	}
	
	public Cobranca agendar(Cobranca cobranca) {
		return cobrancaRepository.salvar(cobranca);
	}
	
	public Cobranca buscar(Long id) {
		Cobranca cobranca = cobrancaRepository.buscar(id);
		if (cobranca == null) {
			throw new EntidadeNaoEncontradaException(String.format("A cobrança de id %d não existe.", id));
		}
		
		return cobranca;
	}
	
}
