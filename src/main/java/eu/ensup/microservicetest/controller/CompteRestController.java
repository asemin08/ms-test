package eu.ensup.microservicetest.controller;

import eu.ensup.microservicetest.entities.Compte;
import eu.ensup.microservicetest.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CompteRestController implements HealthIndicator {

    private CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @GetMapping(path = "/comptes")
    public List<Compte> listCompte(){
        return compteRepository.findAll();
    }

    @GetMapping(path = "/comptes/{id}")
    public Compte getCompte(@PathVariable(name = "id") Long code){
        return compteRepository.findById(code).get();
    }

    @PostMapping(path = "/comptes")
    public Compte save(@RequestBody Compte compte){
        return compteRepository.save(compte);
    }

    @PutMapping(path = "/comptes/{code}")
    public Compte update(@PathVariable Long code, @RequestBody Compte compte){
        compte.setCode(code);
        return compteRepository.save(compte);
    }

    @DeleteMapping(path = "/comptes/{code}")
    public void delete(@PathVariable Long code){
        compteRepository.deleteById(code);
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
