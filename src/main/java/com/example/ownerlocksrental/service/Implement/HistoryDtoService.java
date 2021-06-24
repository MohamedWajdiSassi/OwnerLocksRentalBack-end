package com.example.ownerlocksrental.service.Implement;

import com.example.ownerlocksrental.dto.Device;
import com.example.ownerlocksrental.dto.History;
import com.example.ownerlocksrental.entities.UserHistory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class HistoryDtoService {

    public List<History> allHistoryList (){

        List<History> l = new ArrayList<>();

        UserHistory userh = new UserHistory();

        History history = new History();

        history.getIdReservation();
        history.getPickUpDate();
        history.getReturnDate();
        l.add(history);


        return l ;
    }
}
