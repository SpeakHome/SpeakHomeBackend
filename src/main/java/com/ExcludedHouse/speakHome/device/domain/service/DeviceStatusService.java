package com.ExcludedHouse.speakHome.device.domain.service;

import com.ExcludedHouse.speakHome.chat.domain.model.Chat;
import com.ExcludedHouse.speakHome.device.domain.model.DeviceStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceStatusService {
    List<DeviceStatus> getAll();
    DeviceStatus getById(Long deviceStatusId);
    DeviceStatus create(DeviceStatus deviceStatus);
    DeviceStatus update(Long deviceStatusId, DeviceStatus deviceStatus);
    ResponseEntity<?> delete(Long deviceStatusId);
}
