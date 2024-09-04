package com.criar.pdi.demonstracao.DTOs.ProductInPromotion;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductInPromotionDTO(
        Integer ID,
        @NotNull
        Integer productID,
        @NotNull
        Double price,
        @NotNull
        Integer storeID,
        LocalDate expirationDate,
        LocalDate eventStartDate,
        LocalDate eventEndDate
) implements IGenericDTO {
}
