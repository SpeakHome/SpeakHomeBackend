package com.ExcludedHouse.speakHome.security.domain.service;

import com.ExcludedHouse.speakHome.security.domain.model.Profile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileService {
  List<Profile> getAll();
  Profile getByEmailAndPassword(String userName, String password);
  Profile getById(Long profileId);
  Profile create(Profile profile);
  Profile update(Long profileId, Profile profile);
  ResponseEntity<?> delete(Long profileId);
}
