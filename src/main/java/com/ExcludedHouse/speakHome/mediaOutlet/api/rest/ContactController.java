package com.ExcludedHouse.speakHome.mediaOutlet.api.rest;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.service.ContactService;
import com.ExcludedHouse.speakHome.mediaOutlet.mapping.ContactMapper;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/media-outlet/contacts", produces = "application/json")
public class ContactController {
    private final ContactService contactService;

    private final ContactMapper mapper;

    public ContactController(ContactService contactService, ContactMapper mapper) {
        this.contactService = contactService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<ContactResource> getAllContacts(Pageable pageable) {
        return mapper.modelListPage(contactService.getAll(), pageable);
    }
    @GetMapping("/by-profile/{profileId}/technicians")
    public Page<ContactResource> getTechnicianContactsByProfileId(@PathVariable Long profileId, Pageable pageable) {
        return mapper.modelListPage(contactService.getByProfileIdAndRoleName(profileId, "tecnico"), pageable);
    }

    @GetMapping("/by-profile/{profileId}/non-technicians")
    public Page<ContactResource> getNonTechnicianContactsByProfileId(@PathVariable Long profileId, Pageable pageable) {
        return mapper.modelListPage(contactService.getByProfileIdAndRoleName(profileId, "noTecnico"), pageable);
    }
    @GetMapping("{contactId}")
    public ContactResource getContactById(@PathVariable Long contactId) {
        return mapper.toResource(contactService.getById(contactId));
    }

    @PostMapping
    public ContactResource createContact(@RequestBody CreateContactResource resource) {
        return mapper.toResource(contactService.create(mapper.toModel(resource)));
    }

    @PutMapping("{contactId}")
    public ContactResource updateContact(@PathVariable Long contactId, @RequestBody UpdateContactResource resource) {
        return mapper.toResource(contactService.update(contactId, mapper.toModel(resource)));
    }

    @DeleteMapping("{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable Long contactId) {
        return contactService.delete(contactId);
    }
}
