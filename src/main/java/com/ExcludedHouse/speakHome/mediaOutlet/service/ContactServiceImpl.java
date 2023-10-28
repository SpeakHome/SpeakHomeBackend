package com.ExcludedHouse.speakHome.mediaOutlet.service;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.persistence.ContactRepository;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.service.ContactService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService {
    private static final String ENTITY = "Contact";

    private final ContactRepository contactRepository;

    private final Validator validator;

    public ContactServiceImpl(ContactRepository contactRepository, Validator validator) {
        this.contactRepository = contactRepository;
        this.validator = validator;
    }

    @Override
    public List<Contact> getAll() {return contactRepository.findAll();}

    @Override
    public Contact getById(Long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, contactId));
    }

    @Override
    public Contact create(Contact contact) {
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Long contactId, Contact contact) {
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return contactRepository.findById(contactId)
                .map(contactToUpdate -> contactRepository.save(contactToUpdate
                                .withProfile1Id(contact.getProfile1Id())
                                .withProfile2Id(contact.getProfile2Id())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, contactId));
    }

    @Override
    public ResponseEntity<?> delete(Long contactId) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    contactRepository.delete(contact);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, contactId));
    }
}
