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
public class ReporteDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reporteDiarioId;

    private String tipoSensor;

    private Double valorMinimo;

    private Double valorMaximo;

    private Double valorPromedio;

    private LocalDateTime timestamp;

}
