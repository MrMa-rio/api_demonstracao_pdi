package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreRepository extends JpaRepository<Store, Integer> {

    Page<Store> findAllByNameContains(String name, Pageable pageable);
}
