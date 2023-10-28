package com.ExcludedHouse.speakHome.mediaOutlet.domain.persistence;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
