package com.ExcludedHouse.speakHome.deviceIot.domain.service;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Location;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {
    List<Location> getAll();
    Location getById(Long locationId);
    Location create(Location location);
    Location update(Long locationId, Location location);
    ResponseEntity<?> delete(Long locationId);
}
