package ec.edu.espe.SensorDataCollector.service;

import ec.edu.espe.SensorDataCollector.dto.ResponseDto;
import ec.edu.espe.SensorDataCollector.dto.SensorDataDto;
import ec.edu.espe.SensorDataCollector.model.Sensor;
import ec.edu.espe.SensorDataCollector.model.SensorData;
import ec.edu.espe.SensorDataCollector.repository.SensorDataRepository;
import ec.edu.espe.SensorDataCollector.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorDataService {

    @Autowired
    private NotificacionProducer producer;

    @Autowired
    private ReportTypeProducer reportTypeProducer;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private SensorService sensorService;

    public ResponseDto createSensorData(SensorDataDto dato) {

        SensorData sensorData = new SensorData();

        sensorData.setSensor(sensorService.getSensorData(dato.getSensorId()));
        sensorData.setType(dato.getType());
        sensorData.setValue(dato.getValue());
        sensorData.setTimestamp(dato.getTimestamp());

        SensorData savedSensorData = sensorDataRepository.save(sensorData);

        producer.enviarNotificacion(dato, "nuevo-sensor");

        return new ResponseDto("Datos del sensor guardados correctamente", savedSensorData);

    }

    public ResponseDto listarDatosUnSensor(String sensorId) {
        Sensor sensor = sensorService.getSensorData(sensorId);
        if (sensor == null) {
            throw new RuntimeException("Sensor no encontrado con ID: " + sensorId);
        }

        List<SensorData> sensorData = sensorDataRepository.findAllBySensor_SensorId(sensorId);

        if (sensorData.isEmpty()) {
            throw new RuntimeException("Sensor no encontrado con ID: " + sensorId);
        }

        List<SensorDataDto> sensorDataDtos = sensorData.stream()
                .map(datosSensor -> new SensorDataDto(
                        sensor.getSensorId(),
                        datosSensor.getType(),
                        datosSensor.getValue(),
                        datosSensor.getTimestamp()
                ))
                .collect(Collectors.toList());

        return new ResponseDto("Datos del sensor obtenidos correctamente", sensorDataDtos);
    }

    public ResponseDto getLast24HoursData() {
        LocalDateTime startTime = LocalDateTime.now().minusHours(24);
        List<SensorData> sensorDataList = sensorDataRepository.findAllByTimestampAfter(startTime);
        reportTypeProducer.sendDailyReport(sensorDataList);
        return new ResponseDto("Registros de las últimas 24 horas obtenidos correctamente", sensorDataList);
    }
}
