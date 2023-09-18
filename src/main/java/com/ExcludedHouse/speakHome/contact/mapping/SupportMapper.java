package com.ExcludedHouse.speakHome.contact.mapping;

import com.ExcludedHouse.speakHome.contact.domain.model.Support;
import com.ExcludedHouse.speakHome.contact.resource.CreateSupportResource;
import com.ExcludedHouse.speakHome.contact.resource.SupportResource;
import com.ExcludedHouse.speakHome.contact.resource.UpdateSupportResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SupportMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public SupportResource toResource(Support model) {
        return mapper.map(model, SupportResource.class);
    }

    public Support toModel(CreateSupportResource resource) {
        /*return mapper.map(resource, Chat.class);*/
        Support support = new Support();
        support.setProfile1Id(resource.getProfile1Id());
        support.setProfile2Id(resource.getProfile2Id());
        support.setDevicePermission(resource.getDevicePermission());
        return support;
    }

    public Support toModel(UpdateSupportResource resource) {
        return mapper.map(resource, Support.class);
    }

    public Page<SupportResource> modelListPage(List<Support> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SupportResource.class), pageable, modelList.size());
    }
}
