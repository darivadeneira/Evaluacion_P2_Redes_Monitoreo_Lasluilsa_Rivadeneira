package ec.edu.espe.EnvironmentalAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSensorReadingDto {
    public Long eventId;
    public String sensorId;
    public String type;
    public double value;
    public String timestamp;
}
