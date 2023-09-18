package com.ExcludedHouse.speakHome.mediaOutlet.api.rest;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.service.MessageService;
import com.ExcludedHouse.speakHome.mediaOutlet.mapping.MessageMapper;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.CreateMessageResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.MessageResource;
import com.ExcludedHouse.speakHome.mediaOutlet.resource.UpdateMessageResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/media-outlet/messages", produces = "application/json")
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
