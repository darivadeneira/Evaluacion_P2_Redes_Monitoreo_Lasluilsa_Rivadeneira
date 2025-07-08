package ec.edu.espe.EnvironmentalAnalyzer.config;

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
    public Queue registroCola() {
        return QueueBuilder.durable("registro.cola").build();
    }

    @Bean
    public Queue reporteCola() {
        return QueueBuilder.durable("solicitud-reportes.cola").build();
    }

    @Bean
    public Queue datosReporteDiarioCola() {
        return QueueBuilder.durable("datos-reporte-diario.cola").build();
    }

    @Bean
    public Queue datosReporteInactivosCola() {
        return QueueBuilder.durable("datos-reporte-inactivos.cola").build();
    }
}