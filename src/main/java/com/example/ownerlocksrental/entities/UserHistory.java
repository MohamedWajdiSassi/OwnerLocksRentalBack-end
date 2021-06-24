package com.example.ownerlocksrental.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "UserHistory")
public class UserHistory implements Serializable {
    @Id
    private String idReservation;
    private String pickUpDate;
    private String returnDate;
    private String idDevice;




//    @ManyToOne
//    @JsonIgnore
//    private DeviceInfo deviceInfo;


}
