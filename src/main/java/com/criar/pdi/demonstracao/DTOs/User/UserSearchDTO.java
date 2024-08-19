package com.criar.pdi.demonstracao.DTOs.User;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

public record UserSearchDTO(
        String name,
        String fullName,
        String email,
        String cpf,
        String userAccessLevel) implements IGenericDTO {
    @Override
    public String name() {
        if(name.isEmpty()) return null;
        return name;
    }

    @Override
    public String fullName() {
        if(fullName.isEmpty()) return null;
        return fullName;
    }

    @Override
    public String email() {
        if(email.isEmpty()) return null;
        return email;
    }

    @Override
    public String cpf() {
        if(cpf.isEmpty()) return null;
        return cpf;
    }

    @Override
    public String userAccessLevel() {
        if(userAccessLevel.isEmpty()) return null;
        return userAccessLevel;
    }
}
