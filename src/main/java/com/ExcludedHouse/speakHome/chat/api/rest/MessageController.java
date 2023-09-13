package com.ExcludedHouse.speakHome.chat.api.rest;

import com.ExcludedHouse.speakHome.chat.domain.service.MessageService;
import com.ExcludedHouse.speakHome.chat.mapping.MessageMapper;
import com.ExcludedHouse.speakHome.chat.resource.CreateMessageResource;
import com.ExcludedHouse.speakHome.chat.resource.MessageResource;
import com.ExcludedHouse.speakHome.chat.resource.UpdateMessageResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/messages", produces = "application/json")
public class MessageController {
    private final MessageService messageService;

    private final MessageMapper mapper;

    public MessageController(MessageService messageService, MessageMapper mapper) {
        this.messageService = messageService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<MessageResource> getAllMessages(Pageable pageable) {
        return mapper.modelListPage(messageService.getAll(), pageable);
    }

    @GetMapping("{messageId}")
    public MessageResource getMessageById(@PathVariable Long messageId) {
        return mapper.toResource(messageService.getById(messageId));
    }

    @PostMapping
    public MessageResource createMessage(@RequestBody CreateMessageResource resource) {
        return mapper.toResource(messageService.create(mapper.toModel(resource)));
    }

    @PutMapping("{messageId}")
    public MessageResource updateMessage(@PathVariable Long messageId, @RequestBody UpdateMessageResource resource) {
        return mapper.toResource(messageService.update(messageId, mapper.toModel(resource)));
    }

    @DeleteMapping("{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId) {
        return messageService.delete(messageId);
    }
}
