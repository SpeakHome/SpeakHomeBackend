package com.ExcludedHouse.speakHome.chat.service;

import com.ExcludedHouse.speakHome.chat.domain.model.Chat;
import com.ExcludedHouse.speakHome.chat.domain.persistence.ChatRepository;
import com.ExcludedHouse.speakHome.chat.domain.service.ChatService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {
    private static final String ENTITY = "Chat";

    private final ChatRepository chatRepository;

    private final Validator validator;

    public ChatServiceImpl(ChatRepository chatRepository, Validator validator) {
        this.chatRepository = chatRepository;
        this.validator = validator;
    }

    @Override
    public List<Chat> getAll() {return chatRepository.findAll();}

    @Override
    public Chat getById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, chatId));
    }

    @Override
    public Chat create(Chat chat) {
        Set<ConstraintViolation<Chat>> violations = validator.validate(chat);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return chatRepository.save(chat);
    }

    @Override
    public Chat update(Long chatId, Chat chat) {
        Set<ConstraintViolation<Chat>> violations = validator.validate(chat);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return chatRepository.findById(chatId)
                .map(chatToUpdate -> chatRepository.save(chatToUpdate
                                .withProfile1Id(chat.getProfile1Id())
                                .withProfile2Id(chat.getProfile2Id())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, chatId));
    }

    @Override
    public ResponseEntity<?> delete(Long chatId) {
        return chatRepository.findById(chatId)
                .map(chat -> {
                    chatRepository.delete(chat);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, chatId));
    }
}
