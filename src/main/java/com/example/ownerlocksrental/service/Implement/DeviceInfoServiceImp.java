package com.example.ownerlocksrental.service.Implement;

import com.example.ownerlocksrental.dao.IDeviceInfoDao;
import com.example.ownerlocksrental.entities.DeviceInfo;
import com.example.ownerlocksrental.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DeviceInfoServiceImp implements IDeviceInfoService {
    @Autowired
    private IDeviceInfoDao deviceInfoDao;
    @Override
    public DeviceInfo saveDevice(DeviceInfo deviceInfo) {
        return deviceInfoDao.save(deviceInfo);
    }

    @Override
    public void deleteById(String id) {

        deviceInfoDao.deleteById(id);
    }

    @Override
    public DeviceInfo updateDevice(DeviceInfo deviceInfo) {
        return deviceInfoDao.save(deviceInfo);
    }

    @Override
    public List<DeviceInfo> findALL() {
        return deviceInfoDao.findAll();
    }

    @Override
    public DeviceInfo findById(String id) {
        return deviceInfoDao.findById(id).orElse(null);
    }
}
