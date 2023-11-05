package com.ExcludedHouse.speakHome.mediaOutlet.mapping;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.ContactResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.CreateContactResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.UpdateContactResource;
import com.ExcludedHouse.speakHome.security.domain.service.ProfileService;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ContactMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;
    @Autowired
    private ProfileService profileService;
    public ContactResource toResource(Contact model) {
        return mapper.map(model, ContactResource.class);
    }

    public Contact toModel(CreateContactResource resource) {
        /*return mapper.map(resource, contact.class);*/
        Contact contact = new Contact();
        contact.setProfileId(resource.getProfileId());
        contact.setContactProfile(profileService.getById(resource.getContactProfileId()));
        contact.setDevicePermission(resource.getDevicePermission());
        return contact;
    }

    public Contact toModel(UpdateContactResource resource) {
        return mapper.map(resource, Contact.class);
    }

    public Page<ContactResource> modelListPage(List<Contact> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ContactResource.class), pageable, modelList.size());
    }
}