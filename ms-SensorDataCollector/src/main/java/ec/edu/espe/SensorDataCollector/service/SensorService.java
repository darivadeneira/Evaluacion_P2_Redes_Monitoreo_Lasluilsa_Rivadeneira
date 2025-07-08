package ec.edu.espe.SensorDataCollector.service;

import ec.edu.espe.SensorDataCollector.dto.ResponseDto;
import ec.edu.espe.SensorDataCollector.dto.SensorDto;
import ec.edu.espe.SensorDataCollector.model.Sensor;
import ec.edu.espe.SensorDataCollector.repository.SensorRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorService {

    @Autowired
    private ReportTypeProducer reportTypeProducer;

    @Autowired
    private SensorRepository sensorRepository;

    public Sensor getSensorData(String sensorId) {
        return sensorRepository.findBySensorId(sensorId);
    }

    public ResponseDto createSensor(SensorDto sensorDto) {

        if (sensorDto.getSensorId() == null || sensorDto.getSensorId().isEmpty()) {
            throw new IllegalArgumentException("Sensor ID must be provided.");
        }

        Sensor sensor = new Sensor();
        sensor.setSensorId(sensorDto.getSensorId());
        sensor.setSensorName(sensorDto.getSensorName());

        sensorRepository.save(sensor);

        return new ResponseDto("Sensor created successfully", sensor);
    }

    public ResponseDto getInactiveSensorsInLast24Hours() {
        LocalDateTime startTime = LocalDateTime.now().minusHours(24);
        List<Sensor> inactiveSensors = sensorRepository.findSensorsWithoutActivityInLast24Hours(startTime);
        reportTypeProducer.sendInactiveReport(inactiveSensors);
        return new ResponseDto("Sensores sin actividad en las últimas 24 horas obtenidos correctamente", inactiveSensors);
    }
}
