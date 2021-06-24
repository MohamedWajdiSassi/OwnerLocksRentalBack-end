package com.example.ownerlocksrental.controller;

import com.example.ownerlocksrental.dto.Device;
import com.example.ownerlocksrental.dto.History;
import com.example.ownerlocksrental.entities.DeviceInfo;
import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.entities.UserHistory;
import com.example.ownerlocksrental.service.IDeviceInfoService;
import com.example.ownerlocksrental.service.IOwnerService;
import com.example.ownerlocksrental.service.IUserHistoryService;
import com.example.ownerlocksrental.service.Implement.ClientAppService;
import com.example.ownerlocksrental.service.Implement.HistoryDtoService;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("history")
public class UserHistoryController {
    @Autowired
    private IUserHistoryService userHistoryService;
    @Autowired
    private IOwnerService ownerService;
    @Autowired
    private IDeviceInfoService deviceInfoService;
    @Autowired
    private ClientAppService clientAppService;


    @RequestMapping(value = "creatUserHistory/{idOwner}/{idDevice}/{channel}", method = RequestMethod.POST)
    public ResponseEntity saveHistory(@PathVariable("idOwner") String idOwner,@PathVariable("idDevice") String idDevice,@PathVariable String channel , @RequestBody UserHistory history) throws  Exception {

//        Owner owner = ownerService.findOwnerById(idOwner);
//
//        DeviceInfo device = deviceInfoService.findById(idDevice);
//
//
//
//        UserHistory userHistory =userHistoryService.saveHistory(history);
//        userHistory.setDeviceInfo(device);
//        device.getIdDevice();



        history.setIdReservation(ObjectId.get().toString());
        clientAppService.addHistory(history,channel,idDevice);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteUserHistory/{channel}/{id}", method = RequestMethod.DELETE)
    public void deleteById(@RequestBody String id ,@PathVariable String channel) throws Exception {
       clientAppService.deleteHistory(id,channel);


    }

    @RequestMapping(value = "updateUserHistory/{channel}", method = RequestMethod.PUT)
    public UserHistory updateHistory(@PathVariable String channel,@RequestBody UserHistory history) throws Exception {
        clientAppService.updateHistory(history,channel);
        return userHistoryService.updateHistory(history);
    }

    @RequestMapping(value = "allUserHistory/{channel}/{deviceInfo}", method = RequestMethod.GET)
    public ResponseEntity<?>findALL(@PathVariable String channel , @PathVariable String deviceInfo) throws  Exception {
        byte[] result =clientAppService.getAllHistory(channel , deviceInfo );


        StringBuilder jsonString = new StringBuilder();
        for (byte b: result) {
            jsonString.append((char) b);
        }

        JSONArray array = new JSONArray(jsonString.toString());

        List<UserHistory> histories = new ArrayList<>();
        for (Integer i = 0; i < array.length(); i++) {
            JSONObject secondObject = new JSONObject(array.getJSONObject(i).get("History").toString() );
            UserHistory userHistory = new UserHistory();
            userHistory.setIdReservation(secondObject.get("idReservation").toString());
            userHistory.setPickUpDate(secondObject.get("pickUpDate").toString());
            userHistory.setReturnDate(secondObject.get("returnDate").toString());
            userHistory.setIdDevice(secondObject.get("idDevice").toString());
            History history = new History();
            history.setIdReservation(secondObject.get("idReservation").toString());
            history.setPickUpDate(secondObject.get("pickUpDate").toString());
            history.setReturnDate(secondObject.get("returnDate").toString());


//            device.setIdReservation(secondObject.get("idReservation").toString());
//            device.setPickUpDate(secondObject.get("pickUpDate").toString());
//            device.setReturnDate(secondObject.get(""returnDate).toString());

            histories.add(userHistory);

        }
        StringBuilder jsonsString = new StringBuilder();
        for (byte b: result) {
            jsonsString.append((char) b);
        }
        List<History> his = new ArrayList<>();

        for (Integer i = 0; i < array.length(); i++) {


            JSONObject secondObject = new JSONObject(array.getJSONObject(i).get("history").toString());
            History history = new History();
            history.setIdReservation(secondObject.get("idReservation").toString());
            history.setPickUpDate(secondObject.get("pickUpDate").toString());
            history.setReturnDate(secondObject.get("returnDate").toString());

            HistoryDtoService list = new HistoryDtoService();






            his.add(history);


        }

         return ResponseEntity.status(200).body(histories);
    }
}
