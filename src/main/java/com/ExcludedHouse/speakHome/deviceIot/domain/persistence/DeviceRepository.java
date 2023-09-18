package com.ExcludedHouse.speakHome.deviceIot.domain.persistence;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
