package ec.edu.espe.SensorDataCollector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ResponseDto {
    private String message;
    private Object data;

}
