package com.criar.pdi.demonstracao.DTOs.Payment;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

import java.time.LocalDateTime;

public record PaymentCommonDTO(
        String ID,
        String orderID,
        String methodPayment,
        String status,
        String valuePayment,
        LocalDateTime paymentDate,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}
