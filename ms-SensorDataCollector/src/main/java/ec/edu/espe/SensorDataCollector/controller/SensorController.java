package ec.edu.espe.SensorDataCollector.controller;

import ec.edu.espe.SensorDataCollector.dto.ResponseDto;
import ec.edu.espe.SensorDataCollector.dto.SensorDto;
import ec.edu.espe.SensorDataCollector.model.Sensor;
import ec.edu.espe.SensorDataCollector.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/{sensorId}")
    public Sensor getSensorData(String sensorId) {
        return sensorService.getSensorData(sensorId);
    }

    @PostMapping
    public ResponseDto createSensor(@RequestBody SensorDto sensor) {
        return sensorService.createSensor(sensor);
    }

    @GetMapping("/inactive")
    public ResponseDto getInactiveSensorsInLast24Hours() {
        return sensorService.getInactiveSensorsInLast24Hours();
    }

}
