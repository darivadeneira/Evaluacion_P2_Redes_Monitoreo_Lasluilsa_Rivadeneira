package ec.edu.espe.SensorDataCollector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.SensorDataCollector.model.SensorData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionProducer {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    public void enviarNotificacion(SensorData sensorData, String tipo) {
        try {
            String json = mapper.writeValueAsString(sensorData);
            template.convertAndSend("registro.cola", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}