package ec.edu.espe.SensorDataCollector.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.SensorDataCollector.dto.SensorDataDto;
import ec.edu.espe.SensorDataCollector.service.SensorDataService;
import ec.edu.espe.SensorDataCollector.service.SensorService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RegistroListener {

    @Autowired
    private SensorDataService sensorDataService;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private SensorService sensorService;

    @RabbitListener(queues = "solicitud-reportes.cola")
    public void receiveData(String mensajeJson) {
        try {
            // Convertir el mensaje JSON a un objeto SensorDataDto
            String mensaje = mapper.readValue(mensajeJson, String.class);

            // Procesar el dato recibido
            if (mensaje.equals("diario")) {
                sensorDataService.getLast24HoursData();
            } else if (mensaje.equals("inactivos")) {
                sensorService.getInactiveSensorsInLast24Hours();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
