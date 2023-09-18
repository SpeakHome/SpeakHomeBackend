package com.ExcludedHouse.speakHome.mediaOutlet.domain.persistence;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
