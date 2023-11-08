package com.ExcludedHouse.speakHome.deviceIot.service;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Device;
import com.ExcludedHouse.speakHome.deviceIot.domain.persistence.DeviceRepository;
import com.ExcludedHouse.speakHome.deviceIot.domain.service.DeviceService;
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
    /*
    @Override
    public Device update(Long deviceId, Device device) {
        // Obtener el dispositivo actual de la base de datos
        Device deviceToUpdate = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceId));

        // Aplicar los cambios solo a los campos que se quieren actualizar
        if (device.getName() != null) deviceToUpdate.setName(device.getName());
        if (device.getBaseUrl() != null) deviceToUpdate.setBaseUrl(device.getBaseUrl());
        if (device.getDescription() != null) deviceToUpdate.setDescription(device.getDescription());
        if (device.getPictureUrl() != null) deviceToUpdate.setPictureUrl(device.getPictureUrl());

        // La validación se debería hacer aquí para verificar los campos actualizables
        Set<ConstraintViolation<Device>> violations = validator.validate(deviceToUpdate);
        if (!violations.isEmpty()) throw new ResourceValidationException(ENTITY, violations);

        // Guardar el dispositivo actualizado
        return deviceRepository.save(deviceToUpdate);
    }
    */
    @Override
    public Device update(Long deviceId, Device device) {
        Set<ConstraintViolation<Device>> violations = validator.validate(device);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return deviceRepository.findById(deviceId)
                .map(deviceToUpdate -> deviceRepository.save(deviceToUpdate
                                .withName(device.getName())
                                .withBaseUrl(device.getBaseUrl())
                                .withDescription(device.getDescription())
                                .withPictureUrl(device.getPictureUrl())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceId));
    }

    @Override
    public ResponseEntity<?> delete(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .map(device -> {
                    device.getProfileDevices().clear();
                    deviceRepository.save(device);
                    deviceRepository.delete(device);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, deviceId));
    }
}
