package ec.edu.espe.EnvironmentalAnalyzer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.EnvironmentalAnalyzer.dto.AlertaDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionProducer {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    public void enviarNotificacion(AlertaDto dto) {
        try {
            String json = mapper.writeValueAsString(dto);
            template.convertAndSend("notification.cola", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
