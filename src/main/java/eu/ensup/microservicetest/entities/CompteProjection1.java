package eu.ensup.microservicetest.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name= "comptep1",types = Compte.class)
public interface CompteProjection1 {
    public Long getCode();
    public double getSolde();
}
