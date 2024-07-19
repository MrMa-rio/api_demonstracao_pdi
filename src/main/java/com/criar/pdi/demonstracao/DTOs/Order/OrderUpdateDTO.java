package com.criar.pdi.demonstracao.DTOs.Order;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record OrderUpdateDTO(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        String ID,
        @NumberFormat
        String userID,
        String shoppingCart,
        String deliveryAddress,
        String status
) implements IGenericDTO {
}
