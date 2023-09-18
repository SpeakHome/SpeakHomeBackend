package com.ExcludedHouse.speakHome.deviceIot.domain.persistence;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.ProfileDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDeviceRepository extends JpaRepository<ProfileDevice, Long> {
}
