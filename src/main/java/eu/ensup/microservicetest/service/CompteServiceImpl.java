package eu.ensup.microservicetest.service;

import eu.ensup.microservicetest.entities.Compte;
import eu.ensup.microservicetest.repositories.CompteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private CompteRepository compteRepository;

    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public void virement(Long codeCompteSource, long codeCompteDestination, double montant) {
        Compte c1 = compteRepository.getById(codeCompteSource);
        Compte c2 = compteRepository.getById(codeCompteDestination);
        c1.setSolde(c1.getSolde()-montant);
        c2.setSolde(c2.getSolde() + montant);
        compteRepository.save(c1);
        compteRepository.save(c2);
    }
}
