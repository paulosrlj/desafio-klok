package com.klok.api.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.klok.api.domain.exception.EntidadeEmUsoException;
import com.klok.api.domain.exception.EntidadeNaoEncontradaException;
import com.klok.api.domain.model.Adesao;
import com.klok.api.domain.repository.AdesaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdesaoService {

	AdesaoRepository adesaoRepository;

	public AdesaoService(AdesaoRepository adesaoRepository) {
		this.adesaoRepository = adesaoRepository;
	}

	public Adesao cadastrar(Adesao adesao) {
		return adesaoRepository.salvar(adesao);
	}

	public Adesao buscar(Long id) {
		Adesao adesao = adesaoRepository.buscar(id);
		if (adesao == null) {
			throw new EntidadeNaoEncontradaException(String.format("A adesão de id %d não existe.", id));
		}

		return adesao;
	}

	public void cancelar(Long id) {
		try {
			adesaoRepository.cancelar(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Adesão de id %d não pode se removida, pois está em uso", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe uma adesão de id %d", id));
		}
	}
	
	public Adesao atualizar(Adesao adesao) {
		return adesaoRepository.salvar(adesao);
	}

}
