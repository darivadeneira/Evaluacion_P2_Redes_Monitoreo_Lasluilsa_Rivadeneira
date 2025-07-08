package ec.edu.espe.EnvironmentalAnalyzer.config;

import ec.edu.espe.EnvironmentalAnalyzer.service.ReporteProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    private ReporteProducer reporteProducer;

//    @Scheduled(fixedRate = 86400000)
    @Scheduled(fixedRate = 1000000)
    public void reporteDiario() {
        try {
            System.out.println("Reporte Diario");
            reporteProducer.solicitarReporte("diario");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  //  @Scheduled(fixedRate = 21600000)
    @Scheduled(fixedRate = 10000)
    public void sensoresInactivos() {
        try {
            System.out.println("Sensores Inactivos");
            reporteProducer.solicitarReporte("inactivos");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
