package com.example.ownerlocksrental.controller;

import com.example.ownerlocksrental.dao.IOwnerDao;
import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private IOwnerDao iOwnerDao;
//
//    @GetMapping
//    public ResponseEntity<?> get() {
//        return ResponseEntity.ok("dfsdgb");
//    }
//
//    @PostMapping
//    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
//        Owner owner = new Owner();
//        owner.setEmail(signupRequest.getEmail());
//        owner.setPasswd(passwordEncoder.encode(signupRequest.getPassword()));
//        owner.setUserName(signupRequest.getUsername());
//        iOwnerDao.save(owner);
//        return ResponseEntity.ok("done");
//    }
//
//}
