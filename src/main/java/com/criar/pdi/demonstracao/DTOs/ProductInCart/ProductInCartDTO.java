package com.criar.pdi.demonstracao.DTOs.ProductInCart;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record ProductInCartDTO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        @NotNull
        String productID,
        @NotNull
        String quantity,
        @NotNull
        Double price,
        @NotNull
        String shoppingCartID
) implements IGenericDTO {
}