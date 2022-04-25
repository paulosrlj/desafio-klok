package com.klok.api.infraestructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.klok.api.domain.model.Adesao;
import com.klok.api.domain.repository.AdesaoRepository;

@Repository
public class AdesaoRepositoryImpl implements AdesaoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public Adesao salvar(Adesao adesao) {
		return manager.merge(adesao);
	}

	@Override
	public Adesao buscar(Long id) {
		return manager.find(Adesao.class, id);
	}

	@Override
	@Transactional
	public void cancelar(Long id) {
		Adesao adesao = buscar(id);
		
		if (adesao == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(adesao);
	}

}
