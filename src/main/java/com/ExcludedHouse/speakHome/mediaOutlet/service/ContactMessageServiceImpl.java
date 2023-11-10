package com.ExcludedHouse.speakHome.mediaOutlet.service;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Device;
import com.ExcludedHouse.speakHome.deviceIot.domain.model.ProfileDevice;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.ContactMessage;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Message;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.persistence.ContactMessageRepository;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.service.ContactMessageService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {
    private static final String ENTITY = "ContactMessage";

    private final ContactMessageRepository contactMessageRepository;

    private final Validator validator;

    public ContactMessageServiceImpl(ContactMessageRepository contactMessageRepository, Validator validator) {
        this.contactMessageRepository = contactMessageRepository;
        this.validator = validator;
    }

    @Override
    public List<ContactMessage> getAll() {
        return contactMessageRepository.findAll();
    }

    @Override
    public Page<ContactMessage> getByProfileId(Long profileId, Pageable pageable) {
        return contactMessageRepository.findByContactProfileIdOrContactContactProfileId(profileId, profileId, pageable);
    }

    @Override
    public ContactMessage getById(Long contactMessageId) {
        return contactMessageRepository.findById(contactMessageId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, contactMessageId));
    }

    @Override
    public ContactMessage create(ContactMessage contactMessage) {
        Set<ConstraintViolation<ContactMessage>> violations = validator.validate(contactMessage);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return contactMessageRepository.save(contactMessage);
    }

    @Override
    public ResponseEntity<?> delete(Long contactMessageId) {
        return contactMessageRepository.findById(contactMessageId)
                .map(contact -> {
                    contactMessageRepository.delete(contact);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, contactMessageId));
    }
}
