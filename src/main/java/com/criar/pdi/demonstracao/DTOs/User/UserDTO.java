package com.criar.pdi.demonstracao.DTOs.User;

import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public record UserDTO(
        @Id
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
        Integer adress,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate,
        @NotNull
        UserAccessLevel userAccessLevel
) {}
