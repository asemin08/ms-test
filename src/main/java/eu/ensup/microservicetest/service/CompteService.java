package eu.ensup.microservicetest.service;

public interface CompteService {
    void virement(Long codeCompteSource, long codeCompteDestination, double montant);
}
