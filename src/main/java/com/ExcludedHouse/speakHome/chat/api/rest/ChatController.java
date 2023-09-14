package com.ExcludedHouse.speakHome.chat.api.rest;

import com.ExcludedHouse.speakHome.chat.domain.service.ChatService;
import com.ExcludedHouse.speakHome.chat.mapping.ChatMapper;
import com.ExcludedHouse.speakHome.chat.resource.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/chats", produces = "application/json")
public class ChatController {
    private final ChatService chatService;

    private final ChatMapper mapper;

    public ChatController(ChatService chatService, ChatMapper mapper) {
        this.chatService = chatService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<ChatResource> getAllChats(Pageable pageable) {
        return mapper.modelListPage(chatService.getAll(), pageable);
    }

    @GetMapping("{chatId}")
    public ChatResource getChatById(@PathVariable Long chatId) {
        return mapper.toResource(chatService.getById(chatId));
    }

    @PostMapping
    public ChatResource createChat(@RequestBody CreateChatResource resource) {
        return mapper.toResource(chatService.create(mapper.toModel(resource)));
    }

    @PutMapping("{chatId}")
    public ChatResource updateChat(@PathVariable Long chatId, @RequestBody UpdateChatResource resource) {
        return mapper.toResource(chatService.update(chatId, mapper.toModel(resource)));
    }

    @DeleteMapping("{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable Long chatId) {
        return chatService.delete(chatId);
    }
}
