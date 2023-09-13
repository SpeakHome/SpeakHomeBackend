package com.ExcludedHouse.speakHome.security.mapping;

import com.ExcludedHouse.speakHome.security.domain.model.Role;
import com.ExcludedHouse.speakHome.security.resource.CreateRoleResource;
import com.ExcludedHouse.speakHome.security.resource.RoleResource;
import com.ExcludedHouse.speakHome.security.resource.UpdateRoleResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

//@Component
public class RoleMapper {
  @Autowired
  private EnhancedModelMapper mapper;

  public RoleResource toResource(Role model) {
    return mapper.map(model, RoleResource.class);
  }

  public Role toModel(CreateRoleResource resource) {
    return mapper.map(resource, Role.class);
  }

  public Role toModel(UpdateRoleResource resource) {
    return mapper.map(resource, Role.class);
  }

  public Page<RoleResource> modelListPage(List<Role> modelList, Pageable pageable) {
    return new PageImpl<>(mapper.mapList(modelList, RoleResource.class), pageable, modelList.size());
  }
}
