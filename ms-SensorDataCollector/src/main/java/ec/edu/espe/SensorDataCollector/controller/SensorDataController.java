package ec.edu.espe.SensorDataCollector.controller;

import ec.edu.espe.SensorDataCollector.dto.ResponseDto;
import ec.edu.espe.SensorDataCollector.dto.SensorDataDto;
import ec.edu.espe.SensorDataCollector.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor-readings")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping
    public ResponseDto crearDatosSensor(@RequestBody SensorDataDto dato) {
        return sensorDataService.createSensorData(dato);
    }

    @GetMapping("/{sensorId}")
    public ResponseDto listarDatosUnSensor(@PathVariable String sensorId) {
        return sensorDataService.listarDatosUnSensor(sensorId);
    }

    @GetMapping("/hours")
    public ResponseDto listarDatosUltimasHoras() {
        return sensorDataService.getLast24HoursData();
    }

}
