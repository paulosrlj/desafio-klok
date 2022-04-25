package com.klok.api.infraestructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.klok.api.domain.model.Cobranca;
import com.klok.api.domain.repository.CobrancaRepository;

@Repository
public class CobrancaRepositoryImpl implements CobrancaRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public Cobranca salvar(Cobranca cobranca) {
		return manager.merge(cobranca);
	}

	@Override
	public Cobranca buscar(Long id) {
		return manager.find(Cobranca.class, id);
	}
	
}
