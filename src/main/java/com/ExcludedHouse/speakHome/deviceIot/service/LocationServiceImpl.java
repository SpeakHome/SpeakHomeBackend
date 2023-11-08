package com.ExcludedHouse.speakHome.deviceIot.service;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Location;
import com.ExcludedHouse.speakHome.deviceIot.domain.persistence.LocationRepository;
import com.ExcludedHouse.speakHome.deviceIot.domain.service.LocationService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class LocationServiceImpl implements LocationService {
    private static final String ENTITY = "Location";

    private final LocationRepository locationRepository;

    private final Validator validator;

    public LocationServiceImpl(LocationRepository locationRepository, Validator validator) {
        this.locationRepository = locationRepository;
        this.validator = validator;
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location getById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, locationId));
    }

    @Override
    public Location create(Location location) {
        Set<ConstraintViolation<Location>> violations = validator.validate(location);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return locationRepository.save(location);
    }

    @Override
    public Location update(Long locationId, Location location) {
        Set<ConstraintViolation<Location>> violations = validator.validate(location);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return locationRepository.findById(locationId)
                .map(locationToUpdate -> locationRepository.save(locationToUpdate
                                .withName(location.getName())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, locationId));
    }

    @Override
    public ResponseEntity<?> delete(Long locationId) {
        return locationRepository.findById(locationId)
                .map(location -> {
                    locationRepository.delete(location);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, locationId));
    }
}
