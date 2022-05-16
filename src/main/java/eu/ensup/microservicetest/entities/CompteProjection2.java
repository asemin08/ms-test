package eu.ensup.microservicetest.entities;

import eu.ensup.microservicetest.enums.TypeCompte;
import org.springframework.data.rest.core.config.Projection;

@Projection(name= "comptep2",types = Compte.class)
public interface CompteProjection2 {
    public double getSolde();
    public TypeCompte getType();
}
