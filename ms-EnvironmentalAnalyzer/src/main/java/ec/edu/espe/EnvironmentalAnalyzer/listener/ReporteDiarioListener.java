package ec.edu.espe.EnvironmentalAnalyzer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.EnvironmentalAnalyzer.dto.SensorDataDto;
import ec.edu.espe.EnvironmentalAnalyzer.service.AlertService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReporteDiarioListener {

    @Autowired
    private AlertService alertService;

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = "datos-reporte-diario.cola")
    public void recibirDatosReporteDiario(String mensajeJson) {
        try {
            List<SensorDataDto> datosReporteDiario = mapper.readValue(mensajeJson, mapper.getTypeFactory().constructCollectionType(List.class, SensorDataDto.class));
            alertService.generarReporteDiario(datosReporteDiario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
