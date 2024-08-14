package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Store.Store;
import com.criar.pdi.demonstracao.models.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreRepository extends JpaRepository<Store, Integer> {

    Page<Store> findAllAndByNameContainsAndOwnerIDAndCnpjAndRegion(
            String name,
            String ownerID,
            String cnpj,
            String region,
            Pageable pageable);

    Page<Store> findAllByNameContains(String name, Pageable pageable);

    Page<Store> findAllByOwnerID(String ownerID, Pageable pageable);

    Page<Store> findAllByCnpjContains(String cnpj, Pageable pageable);

    Page<Store> findAllByRegion(String region, Pageable pageable);
}
