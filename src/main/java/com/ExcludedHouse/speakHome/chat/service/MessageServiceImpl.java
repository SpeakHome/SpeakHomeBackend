package com.ExcludedHouse.speakHome.chat.service;

import com.ExcludedHouse.speakHome.chat.domain.model.Chat;
import com.ExcludedHouse.speakHome.chat.domain.model.Message;
import com.ExcludedHouse.speakHome.chat.domain.persistence.ChatRepository;
import com.ExcludedHouse.speakHome.chat.domain.persistence.MessageRepository;
import com.ExcludedHouse.speakHome.chat.domain.service.MessageService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {
    private static final String ENTITY = "Message";

    private final MessageRepository messageRepository;

    private final Validator validator;

    public MessageServiceImpl(MessageRepository messageRepository, Validator validator) {
        this.messageRepository = messageRepository;
        this.validator = validator;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message getById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, messageId));
    }

    @Override
    public Message create(Message message) {
        Set<ConstraintViolation<Message>> violations = validator.validate(message);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return messageRepository.save(message);
    }

    @Override
    public Message update(Long messageId, Message message) {
        Set<ConstraintViolation<Message>> violations = validator.validate(message);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return messageRepository.findById(messageId)
                .map(chatToUpdate -> messageRepository.save(chatToUpdate
                                .withContent(message.getContent())
                                .withCreatedAt(message.getCreatedAt())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, messageId));
    }

    @Override
    public ResponseEntity<?> delete(Long messageId) {
        return messageRepository.findById(messageId)
                .map(chat -> {
                    messageRepository.delete(chat);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, messageId));
    }
}
