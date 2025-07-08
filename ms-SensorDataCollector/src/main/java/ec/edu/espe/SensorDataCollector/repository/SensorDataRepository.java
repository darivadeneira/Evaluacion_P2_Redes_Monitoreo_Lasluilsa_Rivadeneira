package ec.edu.espe.SensorDataCollector.repository;

import ec.edu.espe.SensorDataCollector.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findAllBySensor_SensorId(String sensorId);
}
