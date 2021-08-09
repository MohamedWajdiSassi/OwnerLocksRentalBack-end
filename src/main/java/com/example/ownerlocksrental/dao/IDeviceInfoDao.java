package com.example.ownerlocksrental.dao;

import com.example.ownerlocksrental.entities.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface IDeviceInfoDao extends JpaRepository<DeviceInfo,String> {
}
