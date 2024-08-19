package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    UserDetails findByEmail(String email);

    @Procedure("prc_search_users")
    List<User> searchStoresByParams(String name, String fullName, String email, String cpf, String userAccessLevel); //TODO realizar a paginacao da maneira correta
}
