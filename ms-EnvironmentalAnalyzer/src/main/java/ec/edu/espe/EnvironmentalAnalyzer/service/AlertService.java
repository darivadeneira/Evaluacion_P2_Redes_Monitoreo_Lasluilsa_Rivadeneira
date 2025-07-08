package ec.edu.espe.EnvironmentalAnalyzer.service;

import ec.edu.espe.EnvironmentalAnalyzer.dto.AlertaDto;
import ec.edu.espe.EnvironmentalAnalyzer.dto.NewSensorReadingDto;
import ec.edu.espe.EnvironmentalAnalyzer.model.Alert;
import ec.edu.espe.EnvironmentalAnalyzer.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

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

}
