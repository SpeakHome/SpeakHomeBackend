package com.ExcludedHouse.speakHome.mediaOutlet.domain.persistence;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
