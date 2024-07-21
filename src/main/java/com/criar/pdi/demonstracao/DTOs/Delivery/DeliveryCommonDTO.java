package com.criar.pdi.demonstracao.DTOs.Delivery;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

import java.time.LocalDateTime;

public record DeliveryCommonDTO(
        String ID,
        String orderID,
        String status,
        LocalDateTime shippingDate,
        LocalDateTime deliveryDate,
        String deliveryService,
        String trackingNumber,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate) implements IGenericDTO {
}
