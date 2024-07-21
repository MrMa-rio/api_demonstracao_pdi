package com.criar.pdi.demonstracao.DTOs.Address;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

import java.time.LocalDateTime;

public record AddressCommonDTO(
        String ID,
        String number,
        String street,
        String neighborhood,
        String userID,
        String complement,
        String city,
        String state,
        String zipCode,
        String country,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}
