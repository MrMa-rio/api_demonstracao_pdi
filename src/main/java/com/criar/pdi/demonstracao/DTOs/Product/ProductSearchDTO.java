package com.criar.pdi.demonstracao.DTOs.Product;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

public record ProductSearchDTO(
        String name,
        String description,
        Double price,
        Integer quantity,
        Integer category,
        Integer storeID,
        String images,
        String specification) implements IGenericDTO {
    @Override
    public String name() {
        if (name.isEmpty()) return null;
        return name;
    }

    @Override
    public String description() {
        if (description.isEmpty()) return null;
        return description;
    }

    @Override
    public String images() {
        if (images.isEmpty()) return null;
        return images;
    }

    @Override
    public String specification() {
        if (specification.isEmpty()) return null;
        return specification;
    }
}
