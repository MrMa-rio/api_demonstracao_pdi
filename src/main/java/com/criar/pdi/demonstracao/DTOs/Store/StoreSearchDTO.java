package com.criar.pdi.demonstracao.DTOs.Store;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;


public record StoreSearchDTO(
        String name,
        Integer ownerID,
        String description,
        Integer address,
        Integer region,
        String cnpj) implements IGenericDTO {
    @Override
    public String name() {
        if(name.isEmpty()) return null;
        return name;
    }


    @Override
    public String description() {
        if(description.isEmpty()) return null;
        return description;
    }


    @Override
    public String cnpj() {
        if(cnpj.isEmpty()) return null;
        return cnpj;
    }
}
