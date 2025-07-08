package ec.edu.espe.EnvironmentalAnalyzer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.EnvironmentalAnalyzer.dto.ReporteSensoresInactivosDto;
import ec.edu.espe.EnvironmentalAnalyzer.dto.SensorDataDto;
import ec.edu.espe.EnvironmentalAnalyzer.service.AlertService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteInactivoListener {
    @Autowired
    private AlertService alertService;

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = "datos-reporte-inactivos.cola")
    public void recibirDatosReporteDiario(String mensajeJson) {
        try {
            List<ReporteSensoresInactivosDto> datosReporteSensoresInactivos = mapper.readValue(mensajeJson, mapper.getTypeFactory().constructCollectionType(List.class, ReporteSensoresInactivosDto.class));
            alertService.generarReporteSensoresInactivos(datosReporteSensoresInactivos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
