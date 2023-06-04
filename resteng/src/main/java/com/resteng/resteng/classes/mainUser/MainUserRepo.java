package com.resteng.resteng.classes.mainUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainUserRepo extends JpaRepository<MainUser, Long> {

    Optional<MainUser> findUserByUsername(String username);
}
