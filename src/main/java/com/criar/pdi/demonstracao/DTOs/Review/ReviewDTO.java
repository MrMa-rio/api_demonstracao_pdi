package com.criar.pdi.demonstracao.DTOs.Review;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record ReviewDTO(
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
        String comment
) implements IGenericDTO {
}
