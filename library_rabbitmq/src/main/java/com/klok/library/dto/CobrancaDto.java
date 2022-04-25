package com.klok.library.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CobrancaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal valor;
	private String dono_cobranca;
	private LocalDate data_vencimento;
	private boolean pago = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDono_cobranca() {
		return dono_cobranca;
	}

	public void setDono_cobranca(String dono_cobranca) {
		this.dono_cobranca = dono_cobranca;
	}

	public LocalDate getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(LocalDate data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public CobrancaDto(Long id, BigDecimal valor, String dono_cobranca, LocalDate data_vencimento, boolean pago) {
		this.id = id;
		this.valor = valor;
		this.dono_cobranca = dono_cobranca;
		this.data_vencimento = data_vencimento;
		this.pago = pago;
	}

	public CobrancaDto() {

	}
}
