package com.criar.pdi.demonstracao.DTOs.Product;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

public record ProductSearchDTO(
        String name,
        String description,
        String price,
        String quantity,
        String category,
        String storeID,
        String images,
        String specification) implements IGenericDTO {
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
    public String price() {
        if(price.isEmpty()) return null;
        return price;
    }

    @Override
    public String quantity() {
        if(quantity.isEmpty()) return null;
        return quantity;
    }

    @Override
    public String category() {
        if(category.isEmpty()) return null;
        return category;
    }

    @Override
    public String storeID() {
        if(storeID.isEmpty()) return null;
        return storeID;
    }

    @Override
    public String images() {
        if(images.isEmpty()) return null;
        return images;
    }

    @Override
    public String specification() {
        if(specification.isEmpty()) return null;
        return specification;
    }
}
