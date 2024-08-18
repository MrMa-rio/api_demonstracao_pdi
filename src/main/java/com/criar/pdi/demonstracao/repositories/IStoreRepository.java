package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface IStoreRepository extends JpaRepository<Store, Integer> {
    @Procedure("prc_search_stores")
    List<Store> searchStoresByParams(String name, String ownerID, String description, String address, String region, String cnpj); //TODO realizar a paginacao da maneira correta
}
