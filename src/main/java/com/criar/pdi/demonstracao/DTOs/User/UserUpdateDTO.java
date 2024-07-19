package com.criar.pdi.demonstracao.DTOs.User;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserUpdateDTO(
        @Id @NotBlank
        String ID,
        String name,
        String fullName,
        String email,
        String password,
        @Pattern(regexp = "\\d{11}")
        String cpf,
        UserAccessLevel userAccessLevel
) implements IGenericDTO {}
