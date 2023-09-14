package com.ExcludedHouse.speakHome.device.domain.persistence;

import com.ExcludedHouse.speakHome.device.domain.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
