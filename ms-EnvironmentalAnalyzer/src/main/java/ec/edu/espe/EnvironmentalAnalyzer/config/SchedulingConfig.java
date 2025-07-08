package ec.edu.espe.EnvironmentalAnalyzer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Scheduled(fixedRate = 86400000)
    public void reporteDiario() {
        try {
            System.out.println("Reporte Diario");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 21600000)
    public void sensoresInactivos() {
        try {
            System.out.println("Sensores Inactivos");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
