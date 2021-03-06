package com.example.ownerlocksrental.dao;

import com.example.ownerlocksrental.entities.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface IUserHistoryDao extends JpaRepository<UserHistory, String> {
}
