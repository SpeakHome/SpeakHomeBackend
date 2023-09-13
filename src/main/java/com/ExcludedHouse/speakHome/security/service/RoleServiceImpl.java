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
  public Page<Role> getAll(Pageable pageable) {
    return roleRepository.findAll(pageable);
  }

  @Override
  public Role getById(Long roleId) {
    return roleRepository.findById(roleId)
      .orElseThrow(() -> new ResourceNotFoundException(ENTITY, roleId));
  }
  @Override
  public Role getByName(String roleName) {
    return roleRepository.findByName(roleName)
            .orElseThrow(() -> new ResourceNotFoundException(ENTITY + " with name " + roleName + " not found."));
  }
  @Override
  public Role create(Role role) {
    Set<ConstraintViolation<Role>> violations = validator.validate(role);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    if (roleRepository.findByName(role.getName()).isPresent())
      throw new ResourceValidationException(ENTITY, "An skill with the sme name already exists");

    return roleRepository.save(role);
  }

  @Override
  public Role update(Long roleId, Role role) {
    Set<ConstraintViolation<Role>> violations = validator.validate(role);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    Optional<Role> roleWithName = roleRepository.findByName(role.getName());

    if (roleWithName.isPresent() && !roleWithName.get().getId().equals(role.getId()))
      throw new ResourceValidationException(ENTITY, "A role with the same name already exists");

    return roleRepository.findById(roleId)
      .map(roleToUpdate -> roleRepository.save(roleToUpdate.withName(role.getName())))
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
