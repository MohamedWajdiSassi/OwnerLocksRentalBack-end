package com.example.ownerlocksrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private String idDevice;
    private String title;
    private String location;
    private String deviceCategory;
    private String description;
    private String image;
    private String price;
    private String numBedRooms;
    private String numPerson;
    private String color;
    private String model;
    private List<History> historyList;


}
