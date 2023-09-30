package com.ExcludedHouse.speakHome.security.service;

import com.ExcludedHouse.speakHome.security.domain.model.Role;
import com.ExcludedHouse.speakHome.security.domain.persistence.RoleRepository;
import com.ExcludedHouse.speakHome.security.domain.service.RoleService;
import com.ExcludedHouse.speakHome.shared.exception.ResourceNotFoundException;
import com.ExcludedHouse.speakHome.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
  private static final String ENTITY = "Role";

  private final RoleRepository roleRepository;
  private final Validator validator;

  public RoleServiceImpl(RoleRepository roleRepository, Validator validator) {
    this.roleRepository = roleRepository;
    this.validator = validator;
  }

  @Override
  public List<Role> getAll() {
    return roleRepository.findAll();
  }

  @Override
  public Role getById(Long roleId) {
    return roleRepository.findById(roleId)
      .orElseThrow(() -> new ResourceNotFoundException(ENTITY, roleId));
  }
  @Override
  public Role create(Role role) {
    Set<ConstraintViolation<Role>> violations = validator.validate(role);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    return roleRepository.save(role);
  }

  @Override
  public Role update(Long roleId, Role role) {
    Set<ConstraintViolation<Role>> violations = validator.validate(role);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    return roleRepository.findById(roleId)
      .map(roleToUpdate -> roleRepository.save(roleToUpdate
              .withName(role.getName())
              )
      )
      .orElseThrow(() -> new ResourceNotFoundException(ENTITY, roleId));
  }

  @Override
  public ResponseEntity<?> delete(Long roleId) {
    return roleRepository.findById(roleId)
      .map(role -> {
        roleRepository.delete(role);
        return ResponseEntity.ok().build();
      })
      .orElseThrow(() -> new ResourceNotFoundException(ENTITY, roleId));
  }
}
