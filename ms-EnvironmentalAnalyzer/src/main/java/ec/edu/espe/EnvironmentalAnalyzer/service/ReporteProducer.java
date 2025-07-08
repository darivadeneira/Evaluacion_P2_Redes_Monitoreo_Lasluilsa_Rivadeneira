package ec.edu.espe.EnvironmentalAnalyzer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteProducer {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    public void solicitarReporte(String mensaje) {
        try {
            String json = mapper.writeValueAsString(mensaje);
            template.convertAndSend("solicitud-reportes.cola", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
