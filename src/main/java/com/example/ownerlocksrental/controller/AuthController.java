package com.example.ownerlocksrental.controller;

import com.example.ownerlocksrental.config.jwt.JwtUtils;
import com.example.ownerlocksrental.config.services.UserDetailsImpl;
import com.example.ownerlocksrental.dao.IOwnerDao;
import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.payload.request.SignupRequest;
import com.example.ownerlocksrental.payload.response.JwtResponse;
import com.example.ownerlocksrental.payload.response.MessageResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IOwnerDao ownerDao;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Owner owner) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(owner.getEmail(), owner.getPasswd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()

                ));
    }

    @PostMapping("signUp")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        if (ownerDao.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (ownerDao.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Owner owner = new Owner();
        owner.setIdUser(ObjectId.get().toString());
        owner.setUserName(signUpRequest.getUsername());
        owner.setEmail(signUpRequest.getEmail());
        owner.setPasswd(encoder.encode(signUpRequest.getPassword()));

        ownerDao.save(owner);






        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
