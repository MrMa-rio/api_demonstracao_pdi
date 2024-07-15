package com.criar.pdi.demonstracao.DTOs.Store;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StoreUpdateDTO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        String name,
        String ownerID,
        String description,
        String address,
        String phone,
        String region,
        String cnpj
) implements IGenericDTO {
}