package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.ShoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
