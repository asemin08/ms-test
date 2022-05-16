package eu.ensup.microservicetest.controller;

import eu.ensup.microservicetest.dto.VirementRequestDTO;
import eu.ensup.microservicetest.entities.Compte;
import eu.ensup.microservicetest.repositories.CompteRepository;
import eu.ensup.microservicetest.service.CompteService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AccountRestController implements HealthIndicator {

    private CompteService compteService;
    private CompteRepository compteRepository;

    public AccountRestController(CompteService compteService, CompteRepository compteRepository) {
        this.compteService = compteService;
        this.compteRepository = compteRepository;
    }

    @PutMapping(path = "comptes/virmement")
    public void virement(@RequestBody VirementRequestDTO requestDTO){
        compteService.virement(requestDTO.getCodeCompteSource(), requestDTO.getCodeCompteDestination(), requestDTO.getMontant());
    }

    @Override
    public Health health()
    {
        List<Compte> comptes = compteRepository.findAll();
        if(comptes.isEmpty())
        {
            return Health.down().build();
        }
        return Health.up().build();
    }
}
