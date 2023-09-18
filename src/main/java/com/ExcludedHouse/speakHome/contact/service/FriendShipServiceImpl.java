package com.ExcludedHouse.speakHome.contact.service;

import com.ExcludedHouse.speakHome.contact.domain.model.FriendShip;
import com.ExcludedHouse.speakHome.contact.domain.persistence.FriendShipRepository;
import com.ExcludedHouse.speakHome.contact.domain.service.FriendShipService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FriendShipServiceImpl implements FriendShipService {
    private static final String ENTITY = "FriendShip";

    private final FriendShipRepository friendShipRepository;

    private final Validator validator;

    public FriendShipServiceImpl(FriendShipRepository friendShipRepository, Validator validator) {
        this.friendShipRepository = friendShipRepository;
        this.validator = validator;
    }

    @Override
    public List<FriendShip> getAll() {
        return friendShipRepository.findAll();
    }

    @Override
    public FriendShip getById(Long friendShipId) {
        return friendShipRepository.findById(friendShipId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, friendShipId));
    }

    @Override
    public FriendShip create(FriendShip friendShip) {
        Set<ConstraintViolation<FriendShip>> violations = validator.validate(friendShip);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return friendShipRepository.save(friendShip);
    }

    @Override
    public FriendShip update(Long friendShipId, FriendShip friendShip) {
        Set<ConstraintViolation<FriendShip>> violations = validator.validate(friendShip);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return friendShipRepository.findById(friendShipId)
                .map(friendShipToUpdate -> friendShipRepository.save(friendShipToUpdate
                                .withProfile1Id(friendShip.getProfile1Id())
                                .withProfile2Id(friendShip.getProfile2Id())
                                .withDevicePermission(friendShip.getDevicePermission())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, friendShipId));
    }

    @Override
    public ResponseEntity<?> delete(Long friendShipId) {
        return friendShipRepository.findById(friendShipId)
                .map(friendShip -> {
                    friendShipRepository.delete(friendShip);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, friendShipId));
    }
}
