package com.example.ownerlocksrental.service.Implement;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.ownerlocksrental.dto.Device;
import com.example.ownerlocksrental.dto.History;
import com.example.ownerlocksrental.entities.DeviceInfo;
import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.entities.User;
import com.example.ownerlocksrental.entities.UserHistory;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Slf4j
public class ClientAppService {

    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }
    public Contract contract(String channel) throws IOException {
        URL url = new URL("http://localhost:5984");

        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        //Wallet wallet = Wallets.newCouchDBWallet(walletPath);
        Wallet wallet = Wallets.newCouchDBWallet(url, "onlinewallet");
        // load a CCP
        Path networkConfigPath = Paths.get("/home/medwajdisassi/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/connection-org1.yaml");


        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "wajdi sassi").networkConfig(networkConfigPath).discovery(true);
        Gateway gateway = builder.connect();
        Network network = gateway.getNetwork(channel);
        Contract contract = network.getContract("device");
        return contract;
    }

//    public  static void clientApp ( ) throws Exception {
//        URL url = new URL("http://localhost:5984");
//
//        // Load a file system based wallet for managing identities.
//        Path walletPath = Paths.get("wallet");
//        //Wallet wallet = Wallets.newCouchDBWallet(walletPath);
//        Wallet wallet = Wallets.newCouchDBWallet(url, "onlinewallet");
//        // load a CCP
//        Path networkConfigPath = Paths.get("/home/medwajdisassi/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/connection-org1.yaml");
//
//        Gateway.Builder builder = Gateway.createBuilder();
//        builder.identity(wallet, "khalil ben nasser").networkConfig(networkConfigPath).discovery(true);
//
//        // create a gateway connection
//        try (Gateway gateway = builder.connect()) {
//
//            // get the network and contract
//            Network network = gateway.getNetwork("mychannel");
//            Contract contract = network.getContract("device");
//
//            byte[] result;
//
//
//
////            result = contract.evaluateTransaction("queryAllCars");
////            System.out.println(new String(result));
////
////            contract.submitTransaction("createCar", "CAR10", "VW", "Polo", "Grey", "Mary");
////
////            result = contract.evaluateTransaction("queryCar", "CAR10");
////            System.out.println(new String(result));
//
////
////            contract.submitTransaction("changeCarOwner", "CAR10", "Archie");
////
////            result = contract.evaluateTransaction("queryCar", "CAR10");
////            System.out.println(new String(result));
//        }
//
//    }
    public void addDevice( DeviceInfo deviceInfo , String channel ) throws Exception{

        Contract contract = this.contract(channel);
        byte[] result;
        log.info("createDevice : %s",deviceInfo.toString());


        contract.submitTransaction("CreateDevice"
                ,deviceInfo.getIdDevice()
                ,deviceInfo.getTitle()
                ,deviceInfo.getLocation()
                ,deviceInfo.getDeviceCategory()
                ,deviceInfo.getDescription()
                ,deviceInfo.getImage()
                ,deviceInfo.getPrice().toString()
                ,Integer.toString( deviceInfo.getNumBedRooms())
                ,Integer.toString( deviceInfo.getNumPerson())
                ,deviceInfo.getColor()
                ,deviceInfo.getModel());



    }
    public byte[] getAllDevices (  String channel) throws Exception{
        Contract contract = this.contract(channel);
        byte[] result;
        result = contract.evaluateTransaction("QueryAllDevices");


        //System.out.println(new String(result));

        StringBuilder jsonString = new StringBuilder();
        for (byte b: result) {
            jsonString.append((char) b);
        }
        //JSONObject object = new JSONObject(jsonString.toString());
        JSONArray array = new JSONArray(jsonString.toString());
       // System.out.println(new String(result));
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

//            device.setIdReservation(firstObject.get("idReservation").toString());
//            device.setPickUpDate(firstObject.get("pickUpDate").toString());
//            device.setReturnDate(firstObject.get("returnDate").toString());

//            History history = new History();
//            history.setIdReservation(firstObject.get("idReservation").toString());
//            history.setIdReservation(firstObject.get("pickUpDate").toString());
//            history.setIdReservation(firstObject.get("returnDate").toString());







            devices.add(device);


        }






            return result;
    }
    public byte[] getDevices ( DeviceInfo deviceInfo,String channel ) throws Exception{

        Contract contract = this.contract(channel);
        byte[] result;
        result = contract.evaluateTransaction("queryDevice", deviceInfo.getIdDevice());
        log.info("getOneCar : %s", new String(result));

        return result;
    }
    public void updateDevice ( DeviceInfo deviceInfo, String channel) throws  Exception{
        Contract contract = this.contract(channel);

        log.info(String.format("DEVICE ID : %s, NEW OWNER : %s"
                ,deviceInfo.getIdDevice().toString()
                ,deviceInfo.getTitle()
                ,deviceInfo.getLocation()
                ,deviceInfo.getDeviceCategory()
                ,deviceInfo.getDescription()
                ,deviceInfo.getImage()
                ,deviceInfo.getPrice().toString()
                ,deviceInfo.getNumBedRooms()
                ,deviceInfo.getNumPerson()
                ,deviceInfo.getColor()
                ,deviceInfo.getModel()));
        contract.submitTransaction("ChangeDevice"
                ,deviceInfo.getIdDevice().toString()
                ,deviceInfo.getTitle()
                ,deviceInfo.getLocation()
                ,deviceInfo.getDeviceCategory()
                ,deviceInfo.getDescription()
                ,deviceInfo.getImage()
                ,deviceInfo.getPrice().toString()
                ,Integer.toString( deviceInfo.getNumBedRooms())
                ,Integer.toString( deviceInfo.getNumPerson())
                ,deviceInfo.getColor()
                ,deviceInfo.getModel());


    }
    public void deleteDevice(DeviceInfo deviceInfo,String channel) throws Exception{
        Contract contract = this.contract(channel);
        log.info(String.format("Device Key : %s",deviceInfo.getIdDevice()));
        contract.submitTransaction("DeleteDevices",deviceInfo.getIdDevice());


    }
    public void addHistory(UserHistory history ,String channel ,String idDev) throws Exception {

        Contract contract = this.contract(channel);
        byte[] result;
        log.info("addHistory : %s",history.toString());


        contract.submitTransaction("CreateHistory"
                , history.getIdReservation()
                ,idDev
                , history.getPickUpDate()
                , history.getReturnDate()
        );
    }
    public byte[] getAllHistory (String channel, String deviceInfo ) throws Exception {
        Contract contract = this.contract(channel);
        byte[] result;
        result = contract.evaluateTransaction("QueryAllHistory", deviceInfo);
        System.out.println(new String (result));
        return result ;
    }
    public byte[] getHistory ( UserHistory history, String channel ) throws Exception {

        Contract contract = this.contract(channel);
        byte[] result;
        result = contract.evaluateTransaction("QueryHistory", history.getIdReservation());
        log.info("getOneHistory : %s", new String(result));

        return  result;
    }
    public void updateHistory ( UserHistory history ,String channel) throws  Exception {
        Contract contract = this.contract(channel);
        log.info(String.format("History Key : %s, NEW OWNER : %s", history.getIdReservation()
                , history.getPickUpDate()
                , history.getReturnDate()
        ));
        contract.submitTransaction("ChangeHistory"
                , history.getIdReservation()
                , history.getPickUpDate()
                , history.getReturnDate()
        );

    }
    public void deleteHistory(String idHidtory ,String channel) throws Exception{
        Contract contract = this.contract(channel);

        log.info(String.format("History Id : %s", idHidtory));
        contract.submitTransaction("DeleteHistory",idHidtory);


    }



}
