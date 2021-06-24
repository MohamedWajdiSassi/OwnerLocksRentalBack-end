package com.example.ownerlocksrental.dao;

import com.example.ownerlocksrental.entities.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceInfoDao extends JpaRepository<DeviceInfo,String> {
}
