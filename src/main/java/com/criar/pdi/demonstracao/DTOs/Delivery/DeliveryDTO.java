package com.criar.pdi.demonstracao.DTOs.Delivery;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DeliveryDTO(
        String ID,
        @NotEmpty
        String orderID,
        @NotEmpty
        String status,
        LocalDateTime shippingDate,
        @NotNull
        LocalDateTime deliveryDate,
        @NotNull
        String deliveryService,
        String trackingNumber) implements IGenericDTO {
}
