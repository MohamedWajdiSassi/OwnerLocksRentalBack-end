package com.example.ownerlocksrental.service;

import com.example.ownerlocksrental.entities.Owner;

import java.util.List;
import java.util.Optional;

public interface IOwnerService {
    public Owner saveOwner(Owner owner);

    public void deleteById(String id);

    public Owner updateOwner(Owner owner);


    public List<Owner> findALL();
    public Owner findOwnerById(String id);
}
