package com.ExcludedHouse.speakHome.mediaOutlet.domain.service;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Chat;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {
    List<Chat> getAll();
    Chat getById(Long chatId);
    Chat create(Chat chat);
    Chat update(Long chatId, Chat chat);
    ResponseEntity<?> delete(Long chatId);
}
