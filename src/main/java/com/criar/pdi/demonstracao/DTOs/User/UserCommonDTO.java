package com.criar.pdi.demonstracao.DTOs.User;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserCommonDTO(
        @Id @NotNull
        String ID,
        @NotBlank
        String name,
        @NotBlank
        String fullName,
        @NotBlank
        String email,
        @NotBlank
        String cpf,
        @NotBlank
        UserAccessLevel userAccessLevel,
        @NotBlank
        LocalDateTime inclusionDate,
        LocalDateTime updateDate,
        LocalDateTime exclusionDate)  implements IGenericDTO {}
