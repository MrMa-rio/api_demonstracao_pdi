package com.criar.pdi.demonstracao.DTOs.Store;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StoreDTO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        @NotBlank
        String name,
        @NotNull
        String ownerID,
        String description,
        String address,
        String phone,
        String region,
        @NotNull
        String cnpj,
        LocalDateTime inclusionDate
) implements IGenericDTO {
}
