package com.ExcludedHouse.speakHome.mediaOutlet.domain.persistence;


import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    Page<ContactMessage> findByContactProfileIdOrContactContactProfileId(Long profileId, Long contactProfileId, Pageable pageable);
}
