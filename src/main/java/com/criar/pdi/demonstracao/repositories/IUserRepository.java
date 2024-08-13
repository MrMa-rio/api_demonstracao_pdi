package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.User.User;
import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    UserDetails findByEmail(String email);
    Page<User> findAllByNameContains(String name, Pageable pageable);
    Page<User> findAllByUserAccessLevel(UserAccessLevel userAccessLevel, Pageable pageable);
    Page<User> findAllByNameContainsAndUserAccessLevel(String name, UserAccessLevel userAccessLevel, Pageable pageable);

}
