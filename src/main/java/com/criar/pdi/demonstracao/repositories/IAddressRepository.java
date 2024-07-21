package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}
