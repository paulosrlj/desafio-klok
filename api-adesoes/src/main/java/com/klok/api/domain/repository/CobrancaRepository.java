package com.klok.api.domain.repository;

import com.klok.api.domain.model.Cobranca;

public interface CobrancaRepository {
	Cobranca salvar(Cobranca cobranca);
	Cobranca buscar(Long id);
}
