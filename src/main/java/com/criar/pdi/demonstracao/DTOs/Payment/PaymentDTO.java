package com.criar.pdi.demonstracao.DTOs.Payment;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PaymentDTO(
        String ID,
        @NotNull
        String orderID,
        @NotNull
        String methodPayment,
        @NotNull
        String status,
        @NotNull
        String valuePayment,
        LocalDateTime paymentDate,
        LocalDateTime inclusionDate
) {
}
