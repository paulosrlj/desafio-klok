package com.klok.consumer_api.domain.repository;

import com.klok.consumer_api.domain.model.Cobranca;

public interface CobrancaRepository {
	Cobranca salvar(Cobranca cobranca);
	Cobranca buscar(Long id);
}
