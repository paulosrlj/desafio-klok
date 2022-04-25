package com.klok.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.klok.api.domain.model.Cobranca;
import com.klok.api.domain.repository.CobrancaRepository;
import com.klok.api.domain.service.CobrancaService;
import com.klok.api.util.CobrancaCreator;

@AutoConfigureMockMvc
public class CobrancaServiceTest {

	private final CobrancaRepository cobrancaRepository = Mockito.mock(CobrancaRepository.class);

	private CobrancaService cobrancaService;

	@BeforeEach
	void setup() {
		cobrancaService = new CobrancaService(cobrancaRepository);
	}

	@Test
	public void deveCriarCobrancaCorretamente() {
		Cobranca cobranca = CobrancaCreator.cobrancaToBeCreated();
		Cobranca cobrancaToBeReturn = CobrancaCreator.cobrancaToBeReturned();

		when(cobrancaRepository.salvar(any(Cobranca.class))).thenReturn(cobrancaToBeReturn);
		Cobranca cobrancaSalva = cobrancaService.agendar(cobranca);

		assertThat(cobrancaSalva.getValor()).isNotNull();
		assertThat(cobrancaSalva.getId()).isNotNull();
		assertThat(cobrancaSalva.getDono_cobranca()).isEqualTo("Paulo");
		assertThat(cobrancaSalva.getValor()).isEqualByComparingTo(new BigDecimal(150.87));
	}

	@Test
	public void deveBuscarCobrancaCorretamente() {
		Cobranca cobrancaToBeReturn = CobrancaCreator.cobrancaToBeReturned();

		when(cobrancaRepository.buscar(any(Long.class))).thenReturn(cobrancaToBeReturn);
		Cobranca cobrancaBuscada = cobrancaService.buscar(1L);

		assertThat(cobrancaBuscada.getValor()).isNotNull();
		assertThat(cobrancaBuscada.getId()).isNotNull();
		assertThat(cobrancaBuscada.getValor()).isEqualByComparingTo(new BigDecimal(150.87));
	}

}
