package com.criar.pdi.demonstracao.DTOs.ProductInCartDTO;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductInCartDTO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        @NotNull
        String productID,
        @NotNull
        String quantity,
        @NotNull
        String shoppingCartID
) implements IGenericDTO {
}