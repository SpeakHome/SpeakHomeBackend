package com.ExcludedHouse.speakHome.contact.service;

import com.ExcludedHouse.speakHome.contact.domain.model.Support;
import com.ExcludedHouse.speakHome.contact.domain.persistence.SupportRepository;
import com.ExcludedHouse.speakHome.contact.domain.service.SupportService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SupportServiceImpl implements SupportService {
    private static final String ENTITY = "Support";

    private final SupportRepository supportRepository;

    private final Validator validator;

    public SupportServiceImpl(SupportRepository supportRepository, Validator validator) {
        this.supportRepository = supportRepository;
        this.validator = validator;
    }

    @Override
    public List<Support> getAll() {
        return supportRepository.findAll();
    }

    @Override
    public Support getById(Long supportId) {
        return supportRepository.findById(supportId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, supportId));
    }

    @Override
    public Support create(Support support) {
        Set<ConstraintViolation<Support>> violations = validator.validate(support);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return supportRepository.save(support);
    }

    @Override
    public Support update(Long supportId, Support support) {
        Set<ConstraintViolation<Support>> violations = validator.validate(support);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return supportRepository.findById(supportId)
                .map(supportToUpdate -> supportRepository.save(supportToUpdate
                                .withProfile1Id(support.getProfile1Id())
                                .withProfile2Id(support.getProfile2Id())
                                .withDevicePermission(support.getDevicePermission())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, supportId));
    }

    @Override
    public ResponseEntity<?> delete(Long supportId) {
        return supportRepository.findById(supportId)
                .map(support -> {
                    supportRepository.delete(support);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, supportId));
    }
}
