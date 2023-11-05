package com.ExcludedHouse.speakHome.mediaOutlet.domain.service;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();
    List<Contact> getByProfileIdAndRoleName(Long profileId, String roleName);
    Contact getById(Long contactId);
    Contact create(Contact contact);
    Contact update(Long contactId, Contact contact);
    ResponseEntity<?> delete(Long contactId);
}
