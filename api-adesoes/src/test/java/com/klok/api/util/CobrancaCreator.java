package com.klok.api.util;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.klok.api.domain.model.Cobranca;

public class CobrancaCreator {

	public static Cobranca cobrancaToBeCreated() {
		Cobranca cobranca = new Cobranca();
		cobranca.setData_vencimento(LocalDate.of(2022, 5, 2));
		cobranca.setDono_cobranca("Paulo");
		cobranca.setValor(new BigDecimal(150.87));
		return cobranca;
	}
	
	public static Cobranca cobrancaToBeReturned() {
		Cobranca cobranca = new Cobranca();
		cobranca.setData_vencimento(LocalDate.of(2022, 5, 2));
		cobranca.setDono_cobranca("Paulo");
		cobranca.setValor(new BigDecimal(150.87));
		cobranca.setId(1L);
		return cobranca;
	}
	
}
