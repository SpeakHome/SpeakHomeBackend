package com.ExcludedHouse.speakHome.mediaOutlet.api.rest;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.ContactMessage;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.service.ContactMessageService;
import com.ExcludedHouse.speakHome.mediaOutlet.mapping.ContactMessageMapper;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.ContactMessageResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.CreateContactMessageResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/media-outlet/contact-messages", produces = "application/json")
public class ContactMessageController {
    private final ContactMessageService contactMessageService;
    private final ContactMessageMapper contactMessageMapper;

    public ContactMessageController(ContactMessageService contactMessageService, ContactMessageMapper contactMessageMapper) {
        this.contactMessageService = contactMessageService;
        this.contactMessageMapper = contactMessageMapper;
    }

    @GetMapping
    public Page<ContactMessageResource> getAllContactMessages(Pageable pageable) {
        return contactMessageMapper.modelListPage(contactMessageService.getAll(), pageable);
    }
    @GetMapping("/by-profile/{profileId}")
    public Page<ContactMessageResource> getMessagesByProfileId(@PathVariable Long profileId, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").ascending());
        Page<ContactMessage> page = contactMessageService.getByProfileId(profileId, sortedPageable);
        List<ContactMessage> contactMessages = page.getContent();
        return contactMessageMapper.modelListPage(contactMessages, sortedPageable);
    }
/*
    @GetMapping("/by-profile/{profileId}")
    public Page<ContactMessageResource> getMessagesByProfileId(@PathVariable Long profileId, Pageable pageable) {
        return contactMessageMapper.modelListPage(contactMessageService.getByProfileId(profileId, pageable), pageable);
    }
*/

    @GetMapping("{contactMessageId}")
    public ContactMessageResource getContactMessageById(@PathVariable Long contactMessageId) {
        return contactMessageMapper.toResource(contactMessageService.getById(contactMessageId));
    }

    @PostMapping
    public ContactMessageResource createContactMessage(@RequestBody CreateContactMessageResource resource) {
        return contactMessageMapper.toResource(contactMessageService.create(contactMessageMapper.toModel(resource)));
    }

    @DeleteMapping("{contactMessageId}")
    public ResponseEntity<?> deleteContactMessage(@PathVariable Long contactMessageId) {
        return contactMessageService.delete(contactMessageId);
    }
}