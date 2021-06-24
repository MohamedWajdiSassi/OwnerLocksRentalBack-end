package com.example.ownerlocksrental.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Data
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class User implements Serializable {

    @Id
    private String idUser;
    private String userName;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String email;
    private String phoneNumber;
    private String adress;
    private String nationality;




}
