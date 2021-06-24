package com.example.ownerlocksrental.dao;

import com.example.ownerlocksrental.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOwnerDao extends JpaRepository<Owner, String> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    Optional<Owner> findByEmail(String email);
}
