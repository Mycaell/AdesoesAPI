package com.klok.treinamento.adesoes.api.infrastructure.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {
	
  private static final String NOME_EXCHANGE = "amq.direct";

  private AmqpAdmin amqpAdmin;

  public RabbitMQConection(AmqpAdmin amqpAdmin) {
    this.amqpAdmin = amqpAdmin;
  }

  private Queue fila(String nomeFila) {
    return new Queue(nomeFila, true, false, false);
  }

  private DirectExchange trocaDireta() {
    return new DirectExchange(NOME_EXCHANGE);
  }

  private Binding relacionamento(Queue fila, DirectExchange troca) {
    return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
  }


  @PostConstruct
  private void adiciona() {
    Queue filaCobranca = this.fila(RabbitmqConstantes.FILA_COBRANCA);

    DirectExchange troca = this.trocaDireta();

    Binding ligacaoCobranca = this.relacionamento(filaCobranca, troca);

    this.amqpAdmin.declareQueue(filaCobranca);

    this.amqpAdmin.declareExchange(troca);

    this.amqpAdmin.declareBinding(ligacaoCobranca);
  }
  
  
  
  
  
  
  
  
}
