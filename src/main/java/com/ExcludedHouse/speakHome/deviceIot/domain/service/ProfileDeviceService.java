package com.ExcludedHouse.speakHome.deviceIot.domain.service;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Device;
import com.ExcludedHouse.speakHome.deviceIot.domain.model.ProfileDevice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileDeviceService {
    List<ProfileDevice> getAll();
    List<Device> findDevicesByProfileId(Long profileId);
    ProfileDevice getById(Long profileDeviceId);
    ProfileDevice create(ProfileDevice profileDevice);
    ProfileDevice update(Long profileDeviceId, ProfileDevice profileDevice);
    ResponseEntity<?> delete(Long profileDeviceId);
}
