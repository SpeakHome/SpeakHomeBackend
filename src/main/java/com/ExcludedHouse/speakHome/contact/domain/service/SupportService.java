package com.ExcludedHouse.speakHome.contact.domain.service;

import com.ExcludedHouse.speakHome.contact.domain.model.Support;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SupportService {
    List<Support> getAll();
    Support getById(Long supportId);
    Support create(Support support);
    Support update(Long supportId, Support support);
    ResponseEntity<?> delete(Long supportId);
}
