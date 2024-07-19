package com.criar.pdi.demonstracao.DTOs.Store;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
        @Pattern(regexp = "\\d{14}")
        String cnpj
) implements IGenericDTO {
}