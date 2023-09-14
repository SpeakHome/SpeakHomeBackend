package com.ExcludedHouse.speakHome.device.service;

import com.ExcludedHouse.speakHome.device.domain.model.DeviceStatus;
import com.ExcludedHouse.speakHome.device.domain.persistence.DeviceStatusRepository;
import com.ExcludedHouse.speakHome.device.domain.service.DeviceStatusService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class DeviceStatusServiceImpl implements DeviceStatusService {
    private static final String ENTITY = "DeviceStatus";

    private final DeviceStatusRepository deviceStatusRepository;

    private final Validator validator;

    public DeviceStatusServiceImpl(DeviceStatusRepository deviceStatusRepository, Validator validator) {
        this.deviceStatusRepository = deviceStatusRepository;
        this.validator = validator;
    }

    @Override
    public List<DeviceStatus> getAll() {
        return deviceStatusRepository.findAll();
    }

    @Override
    public DeviceStatus getById(Long deviceStatusId) {
        return deviceStatusRepository.findById(deviceStatusId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceStatusId));
    }

    @Override
    public DeviceStatus create(DeviceStatus deviceStatus) {
        Set<ConstraintViolation<DeviceStatus>> violations = validator.validate(deviceStatus);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return deviceStatusRepository.save(deviceStatus);
    }

    @Override
    public DeviceStatus update(Long deviceStatusId, DeviceStatus deviceStatus) {
        Set<ConstraintViolation<DeviceStatus>> violations = validator.validate(deviceStatus);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return deviceStatusRepository.findById(deviceStatusId)
                .map(deviceStatusToUpdate -> deviceStatusRepository.save(deviceStatusToUpdate
                                .withName(deviceStatus.getName())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceStatusId));
    }

    @Override
    public ResponseEntity<?> delete(Long deviceStatusId) {
        return deviceStatusRepository.findById(deviceStatusId)
                .map(deviceStatus -> {
                    deviceStatusRepository.delete(deviceStatus);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceStatusId));
    }
}
