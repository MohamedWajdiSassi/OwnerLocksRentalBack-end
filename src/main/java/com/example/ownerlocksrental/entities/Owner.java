package com.example.ownerlocksrental.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String email ;
    private String passwd;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DeviceInfo> deviceInfos;



    @OneToOne
    @JsonIgnore
    private UserHistory userHistory;



}
