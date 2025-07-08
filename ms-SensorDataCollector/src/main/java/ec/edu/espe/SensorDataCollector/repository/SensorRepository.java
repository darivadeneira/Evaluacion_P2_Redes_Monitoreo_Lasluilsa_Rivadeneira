package ec.edu.espe.SensorDataCollector.repository;

import ec.edu.espe.SensorDataCollector.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findBySensorId(String sensorId);
}
