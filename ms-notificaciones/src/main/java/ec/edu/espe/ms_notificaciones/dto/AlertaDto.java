package ec.edu.espe.ms_notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDto {
    public Long alertId;
    public String type;
    public String sensorId;
    public double value;
    public double threshold;
    public String timestamp;
}
