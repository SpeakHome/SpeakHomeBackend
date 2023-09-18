package com.ExcludedHouse.speakHome.deviceIot.domain.service;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Device;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceService {
    List<Device> getAll();
    Device getById(Long deviceId);
    Device create(Device device);
    Device update(Long deviceId, Device device);
    ResponseEntity<?> delete(Long deviceId);
}
