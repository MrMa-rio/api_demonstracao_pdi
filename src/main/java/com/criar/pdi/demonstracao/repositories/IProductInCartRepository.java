package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.DTOs.ProductInCartDTO.ProductInCartCommonDTO;
import com.criar.pdi.demonstracao.models.ProductsInCart.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface IProductInCartRepository extends JpaRepository<ProductInCart, Integer> {


    ArrayList<ProductInCartCommonDTO> findAllByShoppingCartID(String shoppingCartID);

}
