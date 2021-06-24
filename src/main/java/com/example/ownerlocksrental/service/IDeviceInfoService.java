package com.example.ownerlocksrental.service;

import com.example.ownerlocksrental.entities.DeviceInfo;

import java.util.List;
import java.util.Optional;

public interface IDeviceInfoService {
    public DeviceInfo saveDevice(DeviceInfo deviceInfo);

    public void deleteById(String id);

    public DeviceInfo updateDevice(DeviceInfo bike);


    public List<DeviceInfo> findALL();
    public DeviceInfo findById(String id);
}
