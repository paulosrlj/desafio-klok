package com.klok.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.klok.api.domain.exception.EntidadeNaoEncontradaException;
import com.klok.api.domain.model.Adesao;
import com.klok.api.domain.repository.AdesaoRepository;
import com.klok.api.domain.service.AdesaoService;
import com.klok.api.util.AdesaoCreator;

@AutoConfigureMockMvc
public class AdesaoServiceTest {

	private final AdesaoRepository adesaoRepository = Mockito.mock(AdesaoRepository.class);

	private AdesaoService adesaoService;

	@BeforeEach
	void setup() {
		adesaoService = new AdesaoService(adesaoRepository);
	}

	@Test
	public void deveCriarAdesaoCorretamente() {
		Adesao adesao = AdesaoCreator.adesaoToBeCreated();
		Adesao adesaoToBeReturn = AdesaoCreator.adesaoToBeReturn();

		when(adesaoRepository.salvar(any(Adesao.class))).thenReturn(adesaoToBeReturn);
		Adesao adesaoSalva = adesaoService.cadastrar(adesao);

		assertThat(adesaoSalva.getValor()).isNotNull();
		assertThat(adesaoSalva.getId()).isNotNull();
		assertThat(adesaoSalva.getValor()).isEqualByComparingTo(new BigDecimal(150.87));
	}

	@Test
	public void deveBuscarAdesaoCorretamente() {
		Adesao adesaoToBeReturn = AdesaoCreator.adesaoToBeReturn();

		when(adesaoRepository.buscar(any(Long.class))).thenReturn(adesaoToBeReturn);
		Adesao adesaoBuscada = adesaoService.buscar(1L);

		assertThat(adesaoBuscada.getValor()).isNotNull();
		assertThat(adesaoBuscada.getId()).isNotNull();
		assertThat(adesaoBuscada.getValor()).isEqualByComparingTo(new BigDecimal(150.87));
	}

	@Test
	public void deveRetornarNuloAoCancelarAdesaoCorretamente() {
		when(adesaoRepository.buscar(any(Long.class))).thenThrow(EntidadeNaoEncontradaException.class);

		adesaoService.cancelar(1L);
		EntidadeNaoEncontradaException entidadeNaoEncontradaException = null;

		try {
			adesaoService.buscar(1L);
		} catch (EntidadeNaoEncontradaException e) {
			entidadeNaoEncontradaException = e;
		}
		
		assertNotNull(entidadeNaoEncontradaException);
	}	
}
