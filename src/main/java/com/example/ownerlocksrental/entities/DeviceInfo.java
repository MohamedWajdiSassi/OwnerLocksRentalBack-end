package com.example.ownerlocksrental.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="DeviceInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class DeviceInfo implements Serializable {

    @Id
    protected String idDevice;
    private String title;
    private String location;
    private String deviceCategory;
    private String description;
    private String image;
    private Integer price;
    private int numBedRooms;
    private int numPerson;
    private String color;
    private String model;






//    @OneToMany(mappedBy = "deviceInfo", cascade = CascadeType.ALL)
//    private List<UserHistory> userHistory;
    @ManyToOne
    @JsonIgnore
    private Owner owner;


}
