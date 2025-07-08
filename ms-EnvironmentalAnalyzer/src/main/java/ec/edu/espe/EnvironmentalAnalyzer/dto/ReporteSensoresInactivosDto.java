package ec.edu.espe.EnvironmentalAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteSensoresInactivosDto {
    private String sensorId;
    private LocalDateTime timestamp;
}
