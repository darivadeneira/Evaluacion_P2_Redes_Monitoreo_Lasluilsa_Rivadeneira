package ec.edu.espe.SensorDataCollector.repository;

import ec.edu.espe.SensorDataCollector.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findBySensorId(String sensorId);

    @Query("SELECT s FROM sensor s WHERE NOT EXISTS (" +
            "SELECT sd FROM sensor_data sd WHERE sd.sensor = s AND sd.timestamp >= :startTime)")
    List<Sensor> findSensorsWithoutActivityInLast24Hours(LocalDateTime startTime);
}
