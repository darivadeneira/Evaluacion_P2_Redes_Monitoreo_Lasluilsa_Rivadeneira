package ec.edu.espe.EnvironmentalAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDataDto {
    private Long eventId;
    private String type;
    private double value;
    private LocalDateTime timestamp;
}
