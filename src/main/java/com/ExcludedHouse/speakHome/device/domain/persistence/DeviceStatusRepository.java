package com.ExcludedHouse.speakHome.device.domain.persistence;

import com.ExcludedHouse.speakHome.device.domain.model.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Long> {
}
