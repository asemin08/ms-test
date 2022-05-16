package eu.ensup.microservicetest;

import eu.ensup.microservicetest.entities.Compte;
import eu.ensup.microservicetest.enums.TypeCompte;
import eu.ensup.microservicetest.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceTestApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Compte.class);
            compteRepository.save(new Compte(null, 9800, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 10800, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 12000, new Date(), TypeCompte.COURANT));
            compteRepository.findAll().forEach(cp ->
                    System.out.println(cp.getCode() + " " + cp.getSolde() + " " + cp.getDateCreation() + " " + cp.getType())
                    );
        };
    }
}
