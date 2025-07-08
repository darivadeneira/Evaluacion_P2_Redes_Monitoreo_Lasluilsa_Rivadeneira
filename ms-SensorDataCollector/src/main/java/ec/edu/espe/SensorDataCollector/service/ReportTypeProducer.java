package ec.edu.espe.SensorDataCollector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.SensorDataCollector.model.Sensor;
import ec.edu.espe.SensorDataCollector.model.SensorData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportTypeProducer {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    public void sendDailyReport(List<SensorData> sensorDataList){
        try{
            String json = mapper.writeValueAsString(sensorDataList);
            template.convertAndSend("datos-reporte-diario.cola", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendInactiveReport(List<Sensor> sensorList){
        try{
            String json = mapper.writeValueAsString(sensorList);
            template.convertAndSend("datos-reporte-inactivos.cola", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
