package eu.ensup.microservicetest.dto;

import lombok.Data;

@Data
public class VirementRequestDTO {
    private Long codeCompteSource;
    private Long codeCompteDestination;
    private double montant;
}
