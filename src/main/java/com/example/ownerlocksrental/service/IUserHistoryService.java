package com.example.ownerlocksrental.service;

import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.entities.UserHistory;

import java.util.List;
import java.util.Optional;

public interface IUserHistoryService {
    public UserHistory saveHistory(UserHistory history);

    public void deleteById(String id);

    public UserHistory updateHistory(UserHistory history);


    public List<UserHistory> findALL();
    public UserHistory findById(String id);
}
