package ca.mcit.bigdata.cricri.stm.enrichment;

import ca.mcit.bigdata.cricri.stm.enrichment.service.StmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class EnrichmentApplication implements CommandLineRunner {

    @Autowired
    private StmService stmService;

    public static void main(String[] args) {
        SpringApplication.run(EnrichmentApplication.class, args);
    }

    @Override
    public void run(String... args) {
        stmService.execute();
    }
}
