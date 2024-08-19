package com.criar.pdi.demonstracao.DTOs.Store;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;


public record StoreSearchDTO(
        String name,
        String ownerID,
        String description,
        String address,
        String region,
        String cnpj) implements IGenericDTO {
    @Override
    public String name() {
        if(name.isEmpty()) return null;
        return name;
    }

    @Override
    public String ownerID() {
        if(ownerID.isEmpty()) return null;
        return ownerID;
    }

    @Override
    public String description() {
        if(description.isEmpty()) return null;
        return description;
    }

    @Override
    public String address() {
        if(address.isEmpty()) return null;
        return address;
    }

    @Override
    public String region() {
        if(region.isEmpty()) return null;
        return region;
    }

    @Override
    public String cnpj() {
        if(cnpj.isEmpty()) return null;
        return cnpj;
    }
}
