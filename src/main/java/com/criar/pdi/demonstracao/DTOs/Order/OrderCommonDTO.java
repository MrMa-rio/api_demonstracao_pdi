package com.criar.pdi.demonstracao.DTOs.Order;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record OrderCommonDTO(
        @NotNull
        String ID,
        @NotNull @NumberFormat
        String userID,
        @NotNull
        String shoppingCartID,
        @NotNull
        String deliveryAddress,
        @NotNull
        String status,
        @NotNull
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}
