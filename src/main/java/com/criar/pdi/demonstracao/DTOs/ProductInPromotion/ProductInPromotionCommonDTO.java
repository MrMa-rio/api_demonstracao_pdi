package com.criar.pdi.demonstracao.DTOs.ProductInPromotion;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductInPromotionCommonDTO(
        Integer ID,
        Integer productID,
        Double price,
        Integer storeID,
        LocalDate expirationDate,
        LocalDate eventStartDate,
        LocalDate eventEndDate,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}
