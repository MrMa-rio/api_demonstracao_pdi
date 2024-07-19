package com.criar.pdi.demonstracao.DTOs.Order;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record OrderDTO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        @NotNull @NumberFormat
        String userID,
        @NotNull
        String shoppingCartID,
        @NotNull
        String deliveryAddress,
        @NotNull
        String status,
        LocalDateTime inclusionDate
) implements IGenericDTO {
}