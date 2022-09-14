package com.veltman.crud.spring.spring.Repository;

import com.veltman.crud.spring.spring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);


}