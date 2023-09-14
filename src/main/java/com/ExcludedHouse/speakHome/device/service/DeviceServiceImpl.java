package com.ExcludedHouse.speakHome.device.service;

import com.ExcludedHouse.speakHome.device.domain.model.Device;
import com.ExcludedHouse.speakHome.device.domain.persistence.DeviceRepository;
import com.ExcludedHouse.speakHome.device.domain.service.DeviceService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class DeviceServiceImpl implements DeviceService {
    private static final String ENTITY = "Device";

    private final DeviceRepository deviceRepository;

    private final Validator validator;

    public DeviceServiceImpl(DeviceRepository deviceRepository, Validator validator) {
        this.deviceRepository = deviceRepository;
        this.validator = validator;
    }

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device getById(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceId));
    }

    @Override
    public Device create(Device device) {
        Set<ConstraintViolation<Device>> violations = validator.validate(device);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return deviceRepository.save(device);
    }

    @Override
    public Device update(Long deviceId, Device device) {
        Set<ConstraintViolation<Device>> violations = validator.validate(device);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return deviceRepository.findById(deviceId)
                .map(deviceToUpdate -> deviceRepository.save(deviceToUpdate
                                .withName(device.getName())
                                .withType(device.getType())
                                .withData(device.getData())
                                .withLastUpdate(device.getLastUpdate())
                                .withIsOnline(device.getIsOnline())
                                .withDescription(device.getDescription())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceId));
    }

    @Override
    public ResponseEntity<?> delete(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .map(device -> {
                    deviceRepository.delete(device);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceId));
    }
}
