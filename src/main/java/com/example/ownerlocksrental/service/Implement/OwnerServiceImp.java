package com.example.ownerlocksrental.service.Implement;

import com.example.ownerlocksrental.dao.IOwnerDao;
import com.example.ownerlocksrental.entities.Owner;
import com.example.ownerlocksrental.service.IOwnerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImp implements IOwnerService {
    @Autowired
    private IOwnerDao ownerDao;


    @Override
    public Owner saveOwner(Owner owner) {
        //owner.setIdUser(ObjectId.get().toString());
        return ownerDao.save(owner);
    }

    @Override
    public void deleteById(String id) {
        ownerDao.deleteById(id);

    }

    @Override
    public Owner updateOwner(Owner owner) {
        return ownerDao.save(owner);
    }

    @Override
    public List<Owner> findALL() {
        return ownerDao.findAll();
    }

    @Override
    public Owner findOwnerById(String id) {
        return ownerDao.findById(id).orElse(null);
    }
}
