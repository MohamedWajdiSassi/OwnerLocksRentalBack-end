package com.example.ownerlocksrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private String idReservation ;
    private String pickUpDate ;
    private String returnDate ;
}
