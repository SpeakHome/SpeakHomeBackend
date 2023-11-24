package com.ExcludedHouse.speakHome.security.mapping;

import com.ExcludedHouse.speakHome.security.domain.model.Profile;
import com.ExcludedHouse.speakHome.security.domain.service.ProfileService;
import com.ExcludedHouse.speakHome.security.domain.service.RoleService;
import com.ExcludedHouse.speakHome.security.resource.CreateProfileResource;
import com.ExcludedHouse.speakHome.security.resource.ProfileResource;
import com.ExcludedHouse.speakHome.security.resource.UpdateProfileResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
//@Component
public class ProfileMapper implements Serializable {
  @Autowired
  private EnhancedModelMapper mapper;

  @Autowired
  private RoleService roleService;

  public ProfileResource toResource(Profile model) {
    return mapper.map(model, ProfileResource.class);
  }

  public Profile toModel(CreateProfileResource resource) {
    Profile profile=new Profile();
    profile.setUserName(resource.getUserName());
    profile.setEmail(resource.getEmail());
    profile.setPassword(resource.getPassword());
    profile.setRole(roleService.getById(resource.getRoleId()));
    return profile;
  }

  public Profile toModel(UpdateProfileResource resource) {
    return mapper.map(resource, Profile.class);
  }

  public Page<ProfileResource> modelListPage(List<Profile> modelList, Pageable pageable) {
    return new PageImpl<>(mapper.mapList(modelList, ProfileResource.class), pageable, modelList.size());
  }
}
