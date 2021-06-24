package com.example.ownerlocksrental.controller;

import com.example.ownerlocksrental.dto.Device;
import com.example.ownerlocksrental.dto.History;
import com.example.ownerlocksrental.entities.DeviceInfo;
import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.service.IDeviceInfoService;
import com.example.ownerlocksrental.service.IOwnerService;
import com.example.ownerlocksrental.service.Implement.ClientAppService;
import com.example.ownerlocksrental.service.Implement.HistoryDtoService;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("deviceInfo")
public class DeviceInfoController {

    @Autowired
    private IOwnerService ownerService;
    @Autowired
    private IDeviceInfoService deviceInfoService;
    @Autowired
    private ClientAppService clientAppService;
//    @Autowired
//    private HistoryDtoService historyDto;


    @RequestMapping(value = "createDevice/{idOwner}/{channel}", method = RequestMethod.POST)
    public ResponseEntity saveDevice(@PathVariable(name="idOwner") String idOwner,@PathVariable String channel, @RequestBody DeviceInfo deviceInfo ) throws Exception {

        Owner owner = ownerService.findOwnerById(idOwner);
        deviceInfo.setIdDevice(ObjectId.get().toString());
        DeviceInfo d =deviceInfoService.saveDevice(deviceInfo);


        d.setOwner(owner);

        owner.getDeviceInfos().add(d);


        clientAppService.addDevice(deviceInfo,channel);


         return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteDevice/{channel}/{idDevice}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable(name="idDevice") String idDevice ,@PathVariable String channel,  DeviceInfo deviceInfo) throws Exception {
        clientAppService.deleteDevice(deviceInfo ,channel);
        return ResponseEntity.status(200).body("done");

    }

    @RequestMapping(value = "updateDevice/{channel}/{idOwner}/{idDevice}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDevice(@PathVariable("idOwner") String idOwner,@PathVariable String channel ,@PathVariable("idDevice") String idDevice,@RequestBody DeviceInfo deviceInfo) throws Exception {
        deviceInfo.setIdDevice(idDevice);
        deviceInfo.setOwner(ownerService.findOwnerById(idOwner));
        clientAppService.updateDevice(deviceInfo,channel);





        return ResponseEntity.status(200).body("done");
    }

    @RequestMapping(value = "allDevices/{owner}/{channel}", method = RequestMethod.GET)
    public ResponseEntity<?> findALL(@PathVariable String owner ,@PathVariable String channel) throws Exception {


        byte[] result = clientAppService.getAllDevices(channel);


        StringBuilder jsonString = new StringBuilder();
        for (byte b: result) {
            jsonString.append((char) b);
        }
        //JSONObject object = new JSONObject(jsonString.toString());
        JSONArray array = new JSONArray(jsonString.toString());
        //System.out.println(new String(result));
//        DeviceInfo deviceInfo = new DeviceInfo(,
//                object.getString("idDevice")
//                ,object.getString("title")
//                ,object.getString("location")
//                ,object.getString("deviceCategory")
//                ,object.getString("description")
//                ,object.getString("image")
//                ,object.getString("price")
//                ,object.getString("numBedRooms")
//                ,object.getString("numPerson")
//                ,object.getString("color")
//                ,object.getString("model")
//
//
//        );
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        for (Integer i = 0; i < array.length(); i++) {
            JSONObject firstObject = new JSONObject( array.getJSONObject(i).get("Device").toString());
            DeviceInfo  deviceInfo = new DeviceInfo();
            deviceInfo.setIdDevice(firstObject.get("idDevice").toString());

            deviceInfo.setTitle(firstObject.get("title").toString());
            deviceInfo.setLocation(firstObject.get("location").toString());
            deviceInfo.setDeviceCategory(firstObject.get("deviceCategory").toString());
            deviceInfo.setDescription(firstObject.get("description").toString());
            deviceInfo.setImage(firstObject.get("image").toString());
            deviceInfo.setPrice(Integer.getInteger( firstObject.getString("price")));
            deviceInfo.setNumBedRooms(Integer.parseInt(firstObject.getString("numBedRooms")));
            deviceInfo.setNumPerson(Integer.parseInt(firstObject.getString("numPerson")));
            deviceInfo.setColor(firstObject.get("color").toString());
            deviceInfo.setModel(firstObject.get("model").toString());




//            JSONObject secondObject = new JSONObject(array.getJSONObject(i).get("History").toString() );

//
//            UserHistory userHistory = new UserHistory();
//            userHistory.setIdReservation(secondObject.get("idReservation").toString());
//            userHistory.setPickUpDate(secondObject.get("pickUpDate").toString());
//            userHistory.setReturnDate(secondObject.get("returnDate").toString());
//
//
//
            deviceInfos.add(deviceInfo);
//
//
//
        }

//        System.out.println(new String(result));
//
//        StringBuilder jsonStrings = new StringBuilder();
//        for (byte b: result) {
//            jsonString.append((char) b);
//        }
//        JSONObject object = new JSONObject(jsonString.toString());
//        JSONArray arrays = new JSONArray(jsonString.toString());
//        System.out.println(new String(result));
        List<Device> devices = new ArrayList<>();

        for (Integer i = 0; i < array.length(); i++) {
            JSONObject firstObject = new JSONObject(array.getJSONObject(i).get("Device").toString());
            Device device = new Device();
            device.setIdDevice(firstObject.get("idDevice").toString());

            device.setTitle(firstObject.get("title").toString());
            device.setLocation(firstObject.get("location").toString());
            device.setDeviceCategory(firstObject.get("deviceCategory").toString());
            device.setDescription(firstObject.get("description").toString());
            device.setImage(firstObject.get("image").toString());
            device.setPrice(firstObject.getString("price"));
            device.setNumBedRooms(firstObject.getString("numBedRooms"));
            device.setNumPerson(firstObject.getString("numPerson"));
            device.setColor(firstObject.get("color").toString());
            device.setModel(firstObject.get("model").toString());
//            device.setHistoryList();
//            JSONObject secondObject = new JSONObject(array.getJSONObject(i).get("history").toString());
//            History history = new History();
//            history.setIdReservation(secondObject.get("idReservation").toString());
//            history.setPickUpDate(secondObject.get("pickUpDate").toString());
//            history.setReturnDate(secondObject.get("returnDate").toString());

            HistoryDtoService list = new HistoryDtoService();




            device.setHistoryList(list.allHistoryList());













            devices.add(device);


        }








        return ResponseEntity.status(200).body(devices);
    }

    @RequestMapping(value = "oneDevice/{channel}/{idDevice}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable(name="idDevice") String idDevice,@PathVariable String channel,DeviceInfo deviceInfo) throws Exception {
        byte[] result  = clientAppService.getDevices(deviceInfo,channel);
        return ResponseEntity.status(200).body(result);
    }
}
