package com.ExcludedHouse.speakHome.chat.domain.persistence;

import com.ExcludedHouse.speakHome.chat.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
