package com.criar.pdi.demonstracao.DTOs.User;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonTypeName
public record UserDTO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        String ID,
        @NotBlank
        String name,
        @NotBlank
        String fullName,
        @Email @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String cpf,
        LocalDateTime inclusionDate,
        @NotNull
        UserAccessLevel userAccessLevel
) implements IGenericDTO {}
