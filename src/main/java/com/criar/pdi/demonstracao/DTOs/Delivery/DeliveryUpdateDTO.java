package com.criar.pdi.demonstracao.DTOs.Delivery;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DeliveryUpdateDTO(
        @NotNull
        String ID,
        String orderID,
        String status,
        LocalDateTime shippingDate,
        LocalDateTime deliveryDate,
        String deliveryService,
        String trackingNumber) implements IGenericDTO {
}
