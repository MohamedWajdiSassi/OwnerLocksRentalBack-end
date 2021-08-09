package com.example.ownerlocksrental.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
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
    @SerializedName("title")
    private String title;
    @SerializedName("location")
    private String location;
    @SerializedName("deviceCategory")
    private String deviceCategory;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private Integer price;
    @SerializedName("numBedRooms")
    private int numBedRooms;
    @SerializedName("numPerson")
    private int numPerson;
    @SerializedName("color")
    private String color;
    @SerializedName("model")
    private String model;






//    @OneToMany(mappedBy = "deviceInfo", cascade = CascadeType.ALL)
//    private List<UserHistory> userHistory;
    @ManyToOne
    @JsonIgnore
    private Owner owner;


}
