package com.ExcludedHouse.speakHome.chat.domain.persistence;

import com.ExcludedHouse.speakHome.chat.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
