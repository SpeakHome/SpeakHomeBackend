package com.ExcludedHouse.speakHome.deviceIot.domain.persistence;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Long> {
}
