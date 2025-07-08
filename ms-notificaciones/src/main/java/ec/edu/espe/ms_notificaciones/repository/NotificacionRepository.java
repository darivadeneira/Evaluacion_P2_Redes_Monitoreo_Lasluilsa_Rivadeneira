package ec.edu.espe.ms_notificaciones.repository;

import ec.edu.espe.ms_notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}
