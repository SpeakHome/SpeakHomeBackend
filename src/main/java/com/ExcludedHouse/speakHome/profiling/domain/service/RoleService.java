package com.ExcludedHouse.speakHome.profiling.domain.service;

import com.ExcludedHouse.speakHome.profiling.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
  List<Role> getAll();
  Page<Role> getAll(Pageable pageable);
  Role getById(Long roleId);
  Role getByName(String roleName);
  Role create(Role role);
  Role update(Long roleId, Role role);
  ResponseEntity<?> delete(Long roleId);
}
