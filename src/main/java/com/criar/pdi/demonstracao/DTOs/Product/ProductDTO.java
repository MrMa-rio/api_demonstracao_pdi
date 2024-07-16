package com.criar.pdi.demonstracao.DTOs.Product;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductDTO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        String name,
        String description,
        Double price,
        Integer quantity,
        @NotNull
        String category,
        @NotBlank
        String store,
        String images,
        String specification,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}
