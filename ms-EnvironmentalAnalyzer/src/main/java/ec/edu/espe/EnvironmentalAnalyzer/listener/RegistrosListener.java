package ec.edu.espe.EnvironmentalAnalyzer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.EnvironmentalAnalyzer.dto.NewSensorReadingDto;
import ec.edu.espe.EnvironmentalAnalyzer.service.AlertService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrosListener {
    @Autowired
    private AlertService service;

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = "registro.cola")
    public void AnalizarRegistro(String mensajeJson) {
        try {
            NewSensorReadingDto dto = mapper.readValue(mensajeJson, NewSensorReadingDto.class);
            service.analizarRegistro(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
