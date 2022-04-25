package com.klok.api.util;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.klok.api.domain.model.Adesao;

public class AdesaoCreator {

	public static Adesao adesaoToBeCreated() {
		Adesao adesao = new Adesao();
		adesao.setData_vencimento(LocalDate.of(2022, 5, 2));
		adesao.setValor(new BigDecimal(150.87));
		return adesao;
	}
	
	public static Adesao adesaoToBeReturn() {
		Adesao adesao = new Adesao();
		adesao.setId(2L);
		adesao.setData_vencimento(LocalDate.of(2022, 5, 2));
		adesao.setPago(false);
		adesao.setValor(new BigDecimal(150.87));
		return adesao;
	}
	
}
