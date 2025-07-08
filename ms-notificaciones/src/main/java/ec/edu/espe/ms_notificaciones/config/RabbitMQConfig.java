package ec.edu.espe.ms_notificaciones.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue alertasCola() {
        return QueueBuilder.durable("alertas.cola").build();
    }

    @Bean
    public Queue notificacionCola() {
        return QueueBuilder.durable("notificacion.cola").build();
    }
}