package com.ExcludedHouse.speakHome.device.domain.persistence;

import com.ExcludedHouse.speakHome.device.domain.model.ProfileDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDeviceRepository extends JpaRepository<ProfileDevice, Long> {
}
