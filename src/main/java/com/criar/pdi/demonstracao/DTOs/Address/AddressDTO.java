package com.criar.pdi.demonstracao.DTOs.Address;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AddressDTO(
        String ID,
        String number,
        @NotEmpty
        String street,
        @NotEmpty
        String neighborhood,
        @NotNull
        String userID,
        String complement,
        @NotEmpty
        String city,
        @NotNull
        String state,
        @NotNull
        String zipCode,
        @NotNull
        String country
) implements IGenericDTO {
}
