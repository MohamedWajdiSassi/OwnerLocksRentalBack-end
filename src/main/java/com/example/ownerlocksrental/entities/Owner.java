package com.example.ownerlocksrental.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Owner")
public class Owner extends  User {

    private String passwd;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<DeviceInfo> deviceInfos;



    @OneToOne
    private UserHistory userHistory;



}
