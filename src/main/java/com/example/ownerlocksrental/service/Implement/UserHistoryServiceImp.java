package com.example.ownerlocksrental.service.Implement;

import com.example.ownerlocksrental.dao.IUserHistoryDao;
import com.example.ownerlocksrental.entities.UserHistory;
import com.example.ownerlocksrental.service.IUserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserHistoryServiceImp implements IUserHistoryService {
    @Autowired
    private IUserHistoryDao userHistoryDao;
    @Override
    public UserHistory saveHistory(UserHistory history) {
        return userHistoryDao.save(history);
    }

    @Override
    public void deleteById(String id) {
        userHistoryDao.deleteById(id);

    }

    @Override
    public UserHistory updateHistory(UserHistory history) {
        return userHistoryDao.save(history);
    }

    @Override
    public List<UserHistory> findALL() {
        return userHistoryDao.findAll();
    }

    @Override
    public UserHistory findById(String id) {
        return userHistoryDao.findById(id).orElse(null);
    }
}
