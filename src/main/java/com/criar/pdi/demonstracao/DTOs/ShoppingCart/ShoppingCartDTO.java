package com.criar.pdi.demonstracao.DTOs.ShoppingCart;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


public record ShoppingCartDTO(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        @NotNull
        String userID
) implements IGenericDTO {
}
