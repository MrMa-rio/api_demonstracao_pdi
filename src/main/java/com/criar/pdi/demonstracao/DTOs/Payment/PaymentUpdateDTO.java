package com.criar.pdi.demonstracao.DTOs.Payment;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PaymentUpdateDTO(
        @NotNull
        String ID,
        String status,
        String methodPayment,
        String valuePayment,
        LocalDateTime paymentDate
        ) implements IGenericDTO {
}
