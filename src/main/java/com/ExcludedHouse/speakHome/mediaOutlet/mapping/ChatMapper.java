package com.ExcludedHouse.speakHome.mediaOutlet.mapping;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Chat;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.ChatResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.CreateChatResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.UpdateChatResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ChatMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ChatResource toResource(Chat model) {
        return mapper.map(model, ChatResource.class);
    }

    public Chat toModel(CreateChatResource resource) {
        /*return mapper.map(resource, Chat.class);*/
        Chat chat = new Chat();
        chat.setProfile1Id(resource.getProfile1Id());
        chat.setProfile2Id(resource.getProfile2Id());
        return chat;
    }

    public Chat toModel(UpdateChatResource resource) {
        return mapper.map(resource, Chat.class);
    }

    public Page<ChatResource> modelListPage(List<Chat> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ChatResource.class), pageable, modelList.size());
    }
}