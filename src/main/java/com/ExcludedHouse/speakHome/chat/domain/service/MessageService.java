package com.ExcludedHouse.speakHome.chat.domain.service;

import com.ExcludedHouse.speakHome.chat.domain.model.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    List<Message> getAll();
    Message getById(Long messageId);
    Message create(Message message);
    Message update(Long messageId, Message message);
    ResponseEntity<?> delete(Long messageId);
}
