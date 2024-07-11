package com.criar.pdi.demonstracao.DTOs.User;

import com.criar.pdi.demonstracao.models.Adress.Adress;
import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
public record UserUpdateDTO(
        @Id @NotNull
        Integer ID,
        String name,
        String fullName,
        String email,
        String password,
        Adress adress,
        Timestamp inclusionDate,
        Timestamp updatedDate,
        Timestamp exclusionDate,
        UserAccessLevel userAccessLevel
) {}
