package com.example.contestbackend.repository;

import com.example.contestbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<UserDetails> findByEmail(String email);

    User findUserByEmail(String email);

    List<User> findUsersByRoleIdIn(List<Integer> id);

}
