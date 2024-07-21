package com.criar.pdi.demonstracao.DTOs.Address;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AddressUpdateDTO(
        @NotNull
        String ID,
        String number,
        String street,
        String neighborhood,
        String userID,
        String complement,
        String city,
        String state,
        String zipCode,
        String country
) implements IGenericDTO {
}
