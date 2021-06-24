package com.example.ownerlocksrental.controller;

import com.example.ownerlocksrental.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @Autowired
    DefaultErrorAttributes errorAttributes;

    @RequestMapping("")
    public ResponseEntity<?> error(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(Collections.singletonList(ErrorAttributeOptions.Include.MESSAGE)));
        return ResponseEntity.status(Integer.parseInt(String.valueOf(attributes.get("status")))).body(new ErrorResponse(
                LocalDateTime.now().toString(), Integer.parseInt(String.valueOf(attributes.get("status"))), String.valueOf(attributes.get("error")), String.valueOf(attributes.get("message")), String.valueOf(attributes.get("path"))
        ));
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
