package com.ExcludedHouse.speakHome.device.service;

import com.ExcludedHouse.speakHome.device.domain.model.ProfileDevice;
import com.ExcludedHouse.speakHome.device.domain.persistence.ProfileDeviceRepository;
import com.ExcludedHouse.speakHome.device.domain.service.ProfileDeviceService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class ProfileDeviceServiceImpl implements ProfileDeviceService {
    private static final String ENTITY = "ProfileDevice";

    private final ProfileDeviceRepository profileDeviceRepository;

    private final Validator validator;

    public ProfileDeviceServiceImpl(ProfileDeviceRepository profileDeviceRepository, Validator validator) {
        this.profileDeviceRepository = profileDeviceRepository;
        this.validator = validator;
    }

    @Override
    public List<ProfileDevice> getAll() {
        return profileDeviceRepository.findAll();
    }

    @Override
    public ProfileDevice getById(Long profileDeviceId) {
        return profileDeviceRepository.findById(profileDeviceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, profileDeviceId));
    }

    @Override
    public ProfileDevice create(ProfileDevice profileDevice) {
        Set<ConstraintViolation<ProfileDevice>> violations = validator.validate(profileDevice);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return profileDeviceRepository.save(profileDevice);
    }

    @Override
    public ProfileDevice update(Long profileDeviceId, ProfileDevice profileDevice) {
        Set<ConstraintViolation<ProfileDevice>> violations = validator.validate(profileDevice);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return profileDeviceRepository.findById(profileDeviceId)
                .map(profileDeviceToUpdate -> profileDeviceRepository.save(profileDeviceToUpdate
                                .withProfileId(profileDevice.getProfileId())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, profileDeviceId));
    }

    @Override
    public ResponseEntity<?> delete(Long profileDeviceId) {
        return profileDeviceRepository.findById(profileDeviceId)
                .map(profileDevice -> {
                    profileDeviceRepository.delete(profileDevice);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, profileDeviceId));
    }
}
