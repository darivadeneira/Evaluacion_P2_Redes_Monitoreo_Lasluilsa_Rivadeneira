package ec.edu.espe.ms_notificaciones.service;

import ec.edu.espe.ms_notificaciones.dto.AlertaDto;
import ec.edu.espe.ms_notificaciones.model.Notificacion;
import ec.edu.espe.ms_notificaciones.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public void guardarNotificacion(AlertaDto alertaDto) {
        Notificacion notificacion = new Notificacion();

        notificacion.setEvent_type(alertaDto.getType());
        notificacion.setSensorId(alertaDto.getSensorId());
        notificacion.setValue(alertaDto.getValue());

        String prioridad = generarPrioridad(alertaDto.getType(), alertaDto.getValue(), alertaDto.getThreshold());

        notificacion.setPriority(prioridad);

        notificacionRepository.save(notificacion);

    }

    public String generarPrioridad(String eventType, double value, double threshold) {

        switch (eventType) {
            case "temperature":
                if (value > (threshold + 10)) {
                    return "HIGH";
                } else if (value > (threshold + 5)) {
                    return "MEDIUM";
                } else {
                    return "LOW";
                }
            case "humidity":
                if (value < (threshold + 5)) {
                    return "HIGH";
                } else if (value > (threshold + 3)) {
                    return "MEDIUM";
                } else {
                    return "LOW";
                }
            case "seismic":
                if (value > (threshold + 2)) {
                    return "HIGH";
                } else if (value > (threshold + 1.5)) {
                    return "MEDIUM";
                } else {
                    return "LOW";
                }
            default:
                return "UNKNOWN";
        }

    }

    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.findAll();
    }
}
