package com.ExcludedHouse.speakHome.security.service;

import com.ExcludedHouse.speakHome.security.domain.persistence.ProfileRepository;
import com.ExcludedHouse.speakHome.security.domain.service.ProfileService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import com.ExcludedHouse.speakHome.security.domain.model.Profile;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {
  private static final String ENTITY = "Profile";

  private final ProfileRepository profileRepository;

  private final Validator validator;

  public ProfileServiceImpl(ProfileRepository profileRepository, Validator validator) {
    this.profileRepository = profileRepository;
    this.validator = validator;
  }

  @Override
  public List<Profile> getAll() {
    return profileRepository.findAll();
  }

  @Override
  public Profile getByEmailAndPassword(String email, String password) {
    return profileRepository.findByEmailAndPassword(email, password)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found with provided credentials."));
  }

  @Override
  public Profile getById(Long profileId) {
    return profileRepository.findById(profileId)
            .orElseThrow(() -> new ResourceNotFoundException(ENTITY, profileId));
  }
  @Override
  public Profile getByUserName(String userName) {
    return profileRepository.findByUserName(userName)
            .orElseThrow(() -> new ResourceNotFoundException(userName));
  }
  @Override
  public Profile create(Profile profile) {
    Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    return profileRepository.save(profile);
  }

  @Override
  public Profile update(Long profileId, Profile profile) {
    Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    return profileRepository.findById(profileId)
      .map(profileToUpdate -> profileRepository.save(profileToUpdate
              .withUserName(profile.getUserName())
              .withEmail(profile.getEmail())
              .withPassword(profile.getPassword())
        )
      )
      .orElseThrow(() -> new ResourceNotFoundException(ENTITY, profileId));
  }

  @Override
  public ResponseEntity<?> delete(Long profileId) {
    return profileRepository.findById(profileId)
      .map(profile -> {
        profileRepository.delete(profile);
        return ResponseEntity.ok().build();
      })
      .orElseThrow(() -> new ResourceNotFoundException(ENTITY, profileId));
  }
}
