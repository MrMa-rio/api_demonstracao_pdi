package com.criar.pdi.demonstracao.DTOs.Review;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record ReviewCommonDTO(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        @NotNull
        String productID,
        @NotNull
        String storeID,
        @NotNull @NumberFormat
        String userID,
        @NotNull
        String ratingStar,
        @NotNull
        String comment,
        @NotNull
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}