package com.ExcludedHouse.speakHome.device.domain.service;

import com.ExcludedHouse.speakHome.chat.domain.model.Chat;
import com.ExcludedHouse.speakHome.device.domain.model.ProfileDevice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileDeviceService {
    List<ProfileDevice> getAll();
    ProfileDevice getById(Long profileDeviceId);
    ProfileDevice create(ProfileDevice profileDevice);
    ProfileDevice update(Long profileDeviceId, ProfileDevice profileDevice);
    ResponseEntity<?> delete(Long profileDeviceId);
}
