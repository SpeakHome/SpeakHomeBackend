package com.ExcludedHouse.speakHome.device.domain.persistence;

import com.ExcludedHouse.speakHome.device.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
