package com.ExcludedHouse.speakHome.deviceIot.domain.persistence;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
