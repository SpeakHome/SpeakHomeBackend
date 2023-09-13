package com.ExcludedHouse.speakHome.chat.mapping;

import com.ExcludedHouse.speakHome.chat.domain.model.Message;
import com.ExcludedHouse.speakHome.chat.resource.CreateMessageResource;
import com.ExcludedHouse.speakHome.chat.resource.MessageResource;
import com.ExcludedHouse.speakHome.chat.resource.UpdateMessageResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class MessageMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public MessageResource toResource(Message model) {
        return mapper.map(model, MessageResource.class);
    }

    public Message toModel(CreateMessageResource resource) {
        return mapper.map(resource, Message.class);
    }

    public Message toModel(UpdateMessageResource resource) {
        return mapper.map(resource, Message.class);
    }

    public Page<MessageResource> modelListPage(List<Message> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, MessageResource.class), pageable, modelList.size());
    }
}
