package com.example.ownerlocksrental.controller;

import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.service.IOwnerService;
import com.example.ownerlocksrental.service.Implement.RegisterUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("owner")
public class OwnerController  {
    @Autowired
    private IOwnerService ownerService;
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping("createOwner")

    public Owner saveOwner(@RequestBody Owner owner)  {

        owner.setIdUser(ObjectId.get().toString());
        ownerService.saveOwner(owner);
        try {
            registerUserService.registerUser(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return owner;
    }


        @RequestMapping(value="deleteOwner/{idOwner}",method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("idOwner") String idOwner) {

        ownerService.deleteById(idOwner);
    }

    @RequestMapping(value = "updateOwner/{idOwner}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOwner(@PathVariable("idOwner") String idOwner , @RequestBody Owner owner) {
        owner.setIdUser(idOwner);
         ownerService.saveOwner(owner);
        return ResponseEntity.status(200).body("saved");
    }

    @RequestMapping(value = "allOwner", method = RequestMethod.GET)
    public List<Owner> findALL() {
        return ownerService.findALL();
    }


    @RequestMapping(value = "oneOwner/{id}", method = RequestMethod.GET)
    public Owner findOwnerById(@PathVariable("id") String id) {
        return ownerService.findOwnerById(id);
    }

}
