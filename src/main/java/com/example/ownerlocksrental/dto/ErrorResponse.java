package com.example.ownerlocksrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String timeStamp;

    private int status;

    private String error;

    private String message;

    private String path;

}
