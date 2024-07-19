package com.criar.pdi.demonstracao.DTOs.ProductInCartDTO;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

public record ProductInCartUpdateDTO(
        @NotNull
        String ID,
        String productID,
        String quantity,
        String shoppingCartID
) implements IGenericDTO {
}