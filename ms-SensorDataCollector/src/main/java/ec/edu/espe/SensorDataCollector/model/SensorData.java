package ec.edu.espe.SensorDataCollector.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String type;
    private double value;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private Sensor sensor;
}
