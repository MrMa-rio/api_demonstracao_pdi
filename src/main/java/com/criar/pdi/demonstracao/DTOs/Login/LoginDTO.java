package com.criar.pdi.demonstracao.DTOs.Login;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull
        String email,
        @NotNull
        String password
) implements IGenericDTO {
}
