package com.ExcludedHouse.speakHome.deviceIot.domain.persistence;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.ProfileDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileDeviceRepository extends JpaRepository<ProfileDevice, Long> {
    List<ProfileDevice> findByProfileId(Long profileId);
}
