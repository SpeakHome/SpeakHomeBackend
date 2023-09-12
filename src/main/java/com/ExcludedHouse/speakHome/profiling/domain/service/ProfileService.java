package com.ExcludedHouse.speakHome.profiling.domain.service;

import com.ExcludedHouse.speakHome.profiling.domain.model.Profile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileService {
  List<Profile> getAll();
  Profile getById(Long profileId);
  Profile getByLastName(String lastName);
  List<Profile> getAllByRoleId(Long roleId);
  Profile create(Profile profile);
  Profile update(Long profileId, Profile profile);
  ResponseEntity<?> delete(Long profileId);
}
