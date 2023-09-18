package com.ExcludedHouse.speakHome.contact.domain.persistence;

import com.ExcludedHouse.speakHome.contact.domain.model.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support, Long> {
}
