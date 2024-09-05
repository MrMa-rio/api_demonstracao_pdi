package com.criar.pdi.demonstracao.DTOs.ProductInOrder;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

import java.time.LocalDateTime;

public record ProductInOrderDTO(
        Integer ID,
        Integer productID,
        Integer quantity,
        Integer orderID,
        Double price) implements IGenericDTO {
}
