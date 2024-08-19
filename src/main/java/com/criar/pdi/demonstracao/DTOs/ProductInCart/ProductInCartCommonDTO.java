package com.criar.pdi.demonstracao.DTOs.ProductInCart;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

import java.time.LocalDateTime;

public record ProductInCartCommonDTO(
        String ID,
        String productID,
        String quantity,
        String shoppingCartID,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate) implements IGenericDTO {
}
