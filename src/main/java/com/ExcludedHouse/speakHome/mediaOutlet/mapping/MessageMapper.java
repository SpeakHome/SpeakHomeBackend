package com.ExcludedHouse.speakHome.mediaOutlet.mapping;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Message;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.CreateMessageResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.MessageResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.UpdateMessageResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MessageMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public MessageResource toResource(Message model) {
        return mapper.map(model, MessageResource.class);
    }

    public Message toModel(CreateMessageResource resource) {
        Message message=new Message();
        message.setContent(resource.getContent());
        message.setCreatedAt(new Date());
        return message;
        //return mapper.map(resource, Message.class);
    }

    public Message toModel(UpdateMessageResource resource) {
        return mapper.map(resource, Message.class);
    }

    public Page<MessageResource> modelListPage(List<Message> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, MessageResource.class), pageable, modelList.size());
    }
}
