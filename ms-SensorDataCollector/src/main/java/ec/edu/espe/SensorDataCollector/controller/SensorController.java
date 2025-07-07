package ec.edu.espe.SensorDataCollector.controller;

import ec.edu.espe.SensorDataCollector.dto.ResponseDto;
import ec.edu.espe.SensorDataCollector.dto.SensorDto;
import ec.edu.espe.SensorDataCollector.model.Sensor;
import ec.edu.espe.SensorDataCollector.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor-readings")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @PostMapping
    public ResponseDto crearDatosSensor(@RequestBody Sensor dato) {
        return sensorService.crarDatosSensor(new SensorDto(
                dato.getSensorId(),
                dato.getType(),
                dato.getValue(),
                dato.getTimestamp()
        ));
    }

    @GetMapping("/{sensorId}")
    public ResponseDto listarDatosUnSensor(@PathVariable String sensorId) {
        return sensorService.listarDatosUnSensor(sensorId);
    }

}
