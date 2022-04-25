package com.klok.consumer_api.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "cobrancas")
public class Cobranca implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@ApiModelProperty(hidden = true)
	private Long id;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true)
	private BigDecimal valor;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true)
	private String dono_cobranca;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true)
	private LocalDate data_vencimento;
	

	@Column(nullable = false)
	@ApiModelProperty(hidden = true)
	private boolean pago = false;
}
