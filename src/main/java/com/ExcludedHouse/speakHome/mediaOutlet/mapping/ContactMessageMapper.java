package com.ExcludedHouse.speakHome.mediaOutlet.mapping;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.ContactMessage;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Message;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.service.ContactService;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.service.MessageService;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.ContactMessageResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.CreateContactMessageResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ContactMessageMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;
    @Autowired
    private ContactService contactService; // Suponiendo que tienes un ContactService
    @Autowired
    private MessageService messageService; // Suponiendo que tienes un MessageService

    public ContactMessageResource toResource(ContactMessage model) {
        return mapper.map(model, ContactMessageResource.class);
    }

    public ContactMessage toModel(CreateContactMessageResource resource) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setContact(contactService.getById(resource.getContactId()));
        contactMessage.setMessage(messageService.getById(resource.getMessageId()));
        return contactMessage;
    }

    public Page<ContactMessageResource> modelListPage(List<ContactMessage> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ContactMessageResource.class), pageable, modelList.size());
    }
}
