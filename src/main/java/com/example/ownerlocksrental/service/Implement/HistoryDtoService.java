package com.example.ownerlocksrental.service.Implement;

import com.example.ownerlocksrental.dto.Device;
import com.example.ownerlocksrental.dto.History;
import com.example.ownerlocksrental.entities.UserHistory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryDtoService {

//    @Autowired
    private ClientAppService clientAppService = new ClientAppService();


    public List<History> allHistoryList (String channel , String device) throws Exception{

        byte [] result = clientAppService.getAllHistory(channel,device);

        List<History> histories = new ArrayList<>();
        if (result.length != 0) {
            StringBuilder jsonString = new StringBuilder();
            for (byte b: result) {
                jsonString.append((char) b);
            }
            System.out.println("##############################################");
            System.out.println(new String(result));

            JSONArray array = new JSONArray(new String(result));

            for (Integer i = 0; i < array.length(); i++) {
                JSONObject secondObject = new JSONObject(array.getJSONObject(i).get("History").toString());
                History history = new History();
                history.setIdReservation(secondObject.get("idReservation").toString());
                history.setPickUpDate(secondObject.get("pickUpDate").toString());
                history.setReturnDate(secondObject.get("returnDate").toString());
                histories.add(history);


            }
        }

        return histories ;
    }
}
