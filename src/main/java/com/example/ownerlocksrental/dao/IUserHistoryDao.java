package com.example.ownerlocksrental.dao;

import com.example.ownerlocksrental.entities.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserHistoryDao extends JpaRepository<UserHistory, String> {
}
