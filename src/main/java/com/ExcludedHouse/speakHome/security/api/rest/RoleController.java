package com.ExcludedHouse.speakHome.security.api.rest;

import com.ExcludedHouse.speakHome.security.domain.service.RoleService;
import com.ExcludedHouse.speakHome.security.mapping.RoleMapper;
import com.ExcludedHouse.speakHome.security.resource.CreateRoleResource;
import com.ExcludedHouse.speakHome.security.resource.RoleResource;
import com.ExcludedHouse.speakHome.security.resource.UpdateRoleResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/security/roles", produces = "application/json")
@Tag(name = "Roles", description = "Create, read, update and delete roles")
public class RoleController {
  private final RoleService roleService;

  private final RoleMapper mapper;

  public RoleController(RoleService roleService, RoleMapper mapper) {
    this.roleService = roleService;
    this.mapper = mapper;
  }

  @GetMapping
  public Page<RoleResource> getAllRoles(Pageable pageable) {
    return mapper.modelListPage(roleService.getAll(), pageable);
  }

  @GetMapping("{roleId}")
  public RoleResource getRoleById(@PathVariable Long roleId) {
    return mapper.toResource(roleService.getById(roleId));
  }

  @PostMapping
  public RoleResource createRole(@RequestBody CreateRoleResource resource) {
    return mapper.toResource(roleService.create(mapper.toModel(resource)));
  }

  @PutMapping("{roleId}")
  public RoleResource updateRole(@PathVariable Long roleId, @RequestBody UpdateRoleResource resource) {
    return mapper.toResource(roleService.update(roleId, mapper.toModel(resource)));
  }

  @DeleteMapping("{roleId}")
  public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
    return roleService.delete(roleId);
  }
}
