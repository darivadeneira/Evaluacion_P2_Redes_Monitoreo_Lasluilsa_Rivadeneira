package ec.edu.espe.EnvironmentalAnalyzer.service;

import ec.edu.espe.EnvironmentalAnalyzer.dto.*;
import ec.edu.espe.EnvironmentalAnalyzer.model.Alert;
import ec.edu.espe.EnvironmentalAnalyzer.model.ReporteDiario;
import ec.edu.espe.EnvironmentalAnalyzer.model.ReporteSensoresInactivos;
import ec.edu.espe.EnvironmentalAnalyzer.repository.AlertRepository;
import ec.edu.espe.EnvironmentalAnalyzer.repository.ReporteDiarioRepository;
import ec.edu.espe.EnvironmentalAnalyzer.repository.ReporteSensoresInactivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private ReporteDiarioRepository reporteDiarioRepository;

    @Autowired
    private ReporteSensoresInactivosRepository reporteSensoresInactivosRepository;

    @Autowired
    private NotificacionProducer notificacionProducer;

    Double umbralTemperatura = 40.0;
    Double umbralHumedad = 20.0;
    Double umbralSismica = 3.0;

    public void analizarRegistro(NewSensorReadingDto dto) {
        boolean triggered = false;
        String alertType = "";
        double threshold = 0;

        switch (dto.type.toLowerCase()) {
            case "temperature":
                if (dto.value > umbralTemperatura) {
                    triggered = true;
                    alertType = "HighTemperatureAlert";
                    threshold = umbralTemperatura;
                }
                break;
            case "humidity":
                if (dto.value < umbralHumedad) {
                    triggered = true;
                    alertType = "LowHumidityWarning";
                    threshold = umbralHumedad;
                }
                break;
            case "seismic":
                if (dto.value > umbralSismica) {
                    triggered = true;
                    alertType = "SeismicActivityDetected";
                    threshold = umbralSismica;
                }
                break;
        }

        if (triggered) {
            Alert alert = new Alert();
            alert.setType(alertType);
            alert.setSensorId(dto.getSensorId());
            alert.setValue(dto.value);
            alert.setThreshold(threshold);
            alert.setTimestamp(dto.timestamp);
            Alert alertaDB = alertRepository.save(alert);

            AlertaDto alertaDto = new AlertaDto();
            alertaDto.setAlertId(alertaDB.getAlertId());
            alertaDto.setType(alertType);
            alertaDto.setSensorId(alertaDto.getSensorId());
            alertaDto.setValue(alertaDto.value);
            alertaDto.setThreshold(threshold);
            alertaDto.setTimestamp(dto.timestamp);
            notificacionProducer.enviarNotificacion(alertaDto);
        }

    }

    public void generarReporteDiario(List<SensorDataDto> dtos) {

        // Agrupar por tipo de sensor
        Map<String, List<SensorDataDto>> agrupadoPorTipo = dtos.stream()
                .collect(Collectors.groupingBy(SensorDataDto::getType));

        for (Map.Entry<String, List<SensorDataDto>> entry : agrupadoPorTipo.entrySet()) {
            String tipo = entry.getKey();
            List<SensorDataDto> lista = entry.getValue();

            Double min = lista.stream().mapToDouble(SensorDataDto::getValue).min().orElse(0.0);
            Double max = lista.stream().mapToDouble(SensorDataDto::getValue).max().orElse(0.0);
            Double promedio = lista.stream().mapToDouble(SensorDataDto::getValue).average().orElse(0.0);

            ReporteDiario reporte = new ReporteDiario();
            reporte.setTipoSensor(tipo);
            reporte.setValorMaximo(max);
            reporte.setValorMinimo(min);
            reporte.setValorPromedio(promedio);
            reporte.setTimestamp(LocalDateTime.now());

            reporteDiarioRepository.save(reporte);
        }

    }

    public void generarReporteSensoresInactivos(List<ReporteSensoresInactivosDto> dtos) {

        List<ReporteSensoresInactivos> reportes = new ArrayList<>();

        for (ReporteSensoresInactivosDto dto : dtos) {
            ReporteSensoresInactivos reporte = new ReporteSensoresInactivos();
            reporte.setSensorId(dto.getSensorId());
            reporte.setTimestamp(LocalDateTime.now());
            reportes.add(reporte);
        }

        reporteSensoresInactivosRepository.saveAll(reportes);

    }

}
