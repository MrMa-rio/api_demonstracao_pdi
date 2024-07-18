package com.criar.pdi.demonstracao.DTOs.ShoppingCart;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ShoppingCartUpdateDTO(
        @NotNull
        String ID,
        @NotNull
        String userID
) {
}
