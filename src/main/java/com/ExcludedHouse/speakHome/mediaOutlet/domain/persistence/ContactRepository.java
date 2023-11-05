package com.ExcludedHouse.speakHome.mediaOutlet.domain.persistence;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import com.ExcludedHouse.speakHome.security.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByProfileIdAndContactProfileRoleName(Long profileId, String roleName);
}