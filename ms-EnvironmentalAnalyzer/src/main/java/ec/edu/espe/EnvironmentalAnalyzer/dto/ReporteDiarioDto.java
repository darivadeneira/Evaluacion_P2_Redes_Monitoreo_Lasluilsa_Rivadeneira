package ec.edu.espe.EnvironmentalAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDiarioDto {
    private String tipoSensor;
    private Double valorMinimo;
    private Double valorMaximo;
    private Double valorPromedio;
    private LocalDateTime timestamp;
}
