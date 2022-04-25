package com.klok.api.domain.repository;

import com.klok.api.domain.model.Adesao;

public interface AdesaoRepository {
	Adesao salvar(Adesao adesao);
	Adesao buscar(Long id);
	void cancelar(Long id);
}
