package com.ExcludedHouse.speakHome.mediaOutlet.domain.service;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.ContactMessage;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactMessageService {
    List<ContactMessage> getAll();
    Page<ContactMessage> getByProfileId(Long profileId, Pageable pageable);
    ContactMessage getById(Long contactMessageId);
    ContactMessage create(ContactMessage contactMessage);
    ResponseEntity<?> delete(Long contactMessageId);
}
