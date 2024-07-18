package com.criar.pdi.demonstracao.DTOs.ShoppingCart;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ShoppingCartCommonDTO(
        @NotNull
        String ID,
        @NotNull
        String userID,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}
