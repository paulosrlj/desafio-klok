package com.klok.consumer_api.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.klok.consumer_api.domain.model.Cobranca;
import com.klok.consumer_api.domain.service.CobrancaService;
import com.klok.library.dto.CobrancaDto;
import com.klok.library.rabbitmq.connections.constants.RabbitMQConstants;

@Component
public class CobrancaConsumer {
	
	@Autowired
	CobrancaService cobrancaService;
	
	@RabbitListener(queues = RabbitMQConstants.FILA_COBRANCA)
	private void consumirCobranca(CobrancaDto cobrancaDto) {
		Cobranca cobranca = new Cobranca();
		
		BeanUtils.copyProperties(cobrancaDto, cobranca);
		
		cobrancaService.salvar(cobranca);
	}
}
