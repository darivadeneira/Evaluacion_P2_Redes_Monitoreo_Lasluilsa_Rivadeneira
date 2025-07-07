package ec.edu.espe.SensorDataCollector.service;

import ec.edu.espe.SensorDataCollector.dto.ResponseDto;
import ec.edu.espe.SensorDataCollector.dto.SensorDto;
import ec.edu.espe.SensorDataCollector.model.Sensor;
import ec.edu.espe.SensorDataCollector.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    @Autowired
    private NotificacionProducer producer;

    @Autowired
    private SensorRepository sensorRepository;

    public ResponseDto crarDatosSensor(SensorDto dato) {

        Sensor sensor = new Sensor();

        sensor.setSensorId(dato.getSensorId());
        sensor.setType(dato.getType());
        sensor.setValue(dato.getValue());
        sensor.setTimestamp(dato.getTimestamp());

        Sensor savedSensor = sensorRepository.save(sensor);

        producer.enviarNotificacion(savedSensor, "nuevo-sensor");

        return new ResponseDto("Datos del sensor guardados correctamente", savedSensor);

    }

    public ResponseDto listarDatosUnSensor(String sensorId) {
        List<Sensor> sensors = sensorRepository.findAllBySensorId(sensorId);
        
        if (sensors.isEmpty()) {
            throw new RuntimeException("Sensor no encontrado con ID: " + sensorId);
        }

        List<SensorDto> sensorDtos = sensors.stream()
                .map(sensor -> new SensorDto(
                        sensor.getSensorId(),
                        sensor.getType(),
                        sensor.getValue(),
                        sensor.getTimestamp()
                ))
                .collect(Collectors.toList());
        
        return new ResponseDto("Datos del sensor obtenidos correctamente", sensorDtos);
    }

    //devolver información por tipo de sensor en un rango de 24 horas (temperature, humidity, seismic)
    public ResponseDto listarDatosPorTipoYFecha(String type, String startDate, String endDate) {
        List<Sensor> sensors = sensorRepository.findAllByTypeAndTimestampBetween(type, startDate, endDate);

        if (sensors.isEmpty()) {
            throw new RuntimeException("No se encontraron datos para el tipo: " + type + " en el rango de fechas especificado.");
        }

        List<SensorDto> sensorDtos = sensors.stream()
                .map(sensor -> new SensorDto(
                        sensor.getSensorId(),
                        sensor.getType(),
                        sensor.getValue(),
                        sensor.getTimestamp()
                ))
                .collect(Collectors.toList());

        return new ResponseDto("Datos obtenidos correctamente", sensorDtos);
    }

}
