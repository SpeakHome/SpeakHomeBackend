package com.ExcludedHouse.speakHome.security.domain.service;

import com.ExcludedHouse.speakHome.security.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
  List<Role> getAll();
  Role getById(Long roleId);
  Role create(Role role);
  Role update(Long roleId, Role role);
  ResponseEntity<?> delete(Long roleId);
}
