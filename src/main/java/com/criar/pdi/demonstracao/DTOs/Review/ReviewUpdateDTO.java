package com.criar.pdi.demonstracao.DTOs.Review;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record ReviewUpdateDTO(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        String ID,
        String productID,
        String storeID,
        String userID,
        String ratingStar,
        String comment
) implements IGenericDTO {
}