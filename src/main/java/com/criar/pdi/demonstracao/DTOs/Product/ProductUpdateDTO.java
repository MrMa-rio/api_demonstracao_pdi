package com.criar.pdi.demonstracao.DTOs.Product;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateDTO(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        String ID,
        String name,
        String description,
        String price,
        Integer quantity,
        String category,
        String store,
        String images,
        String specification
) implements IGenericDTO {
}
