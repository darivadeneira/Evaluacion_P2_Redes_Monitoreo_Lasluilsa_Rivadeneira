package ec.edu.espe.EnvironmentalAnalyzer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity()
public class ReporteSensoresInactivos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reporteSensoresInactivosId;

    private String sensorId;
    private LocalDateTime timestamp;
}
