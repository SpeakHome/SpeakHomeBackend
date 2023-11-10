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

    //public ContactMessage toModel(CreateContactMessageResource resource) { return mapper.map(resource, ContactMessage.class); }
    //@Transactional
    public ContactMessage toModel(CreateContactMessageResource resource) {
        // Crea la instancia de ContactMessage
        ContactMessage contactMessage = new ContactMessage();

        // Usa los servicios para cargar las instancias de Contact y Message basadas en los IDs proporcionados
        Contact contact = contactService.getById(resource.getContactId());
        Message message = messageService.getById(resource.getMessageId());

        // Asigna las entidades encontradas a la nueva instancia de ContactMessage
        contactMessage.setContact(contact);
        contactMessage.setMessage(message);

        return contactMessage;
    }

    public Page<ContactMessageResource> modelListPage(List<ContactMessage> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ContactMessageResource.class), pageable, modelList.size());
    }
}
