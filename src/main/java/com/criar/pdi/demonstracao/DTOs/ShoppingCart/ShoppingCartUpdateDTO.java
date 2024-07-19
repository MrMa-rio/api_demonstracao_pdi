package com.criar.pdi.demonstracao.DTOs.ShoppingCart;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record ShoppingCartUpdateDTO(
        @NotNull
        String ID,
        @NotNull @NumberFormat
        String userID
) {
}
