package ec.edu.espe.SensorDataCollector.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "sensor")
public class Sensor {

    @Id
    @Column(unique = true)
    private String sensorId;

    private String sensorName;

    @OneToMany(mappedBy = "sensor")
    @JsonIgnore
    private List<SensorData> sensorDataList;
}
