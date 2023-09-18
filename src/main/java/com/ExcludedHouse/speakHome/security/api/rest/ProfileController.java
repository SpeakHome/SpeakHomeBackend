package com.ExcludedHouse.speakHome.security.api.rest;

import com.ExcludedHouse.speakHome.security.domain.service.ProfileService;
import com.ExcludedHouse.speakHome.security.mapping.ProfileMapper;
import com.ExcludedHouse.speakHome.security.resource.CreateProfileResource;
import com.ExcludedHouse.speakHome.security.resource.ProfileResource;
import com.ExcludedHouse.speakHome.security.resource.UpdateProfileResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/security/profiles", produces = "application/json")
@Tag(name = "Profiles", description = "Create, read, update and delete profiles")
public class ProfileController {
  private final ProfileService profileService;

  private final ProfileMapper mapper;

  public ProfileController(ProfileService profileService, ProfileMapper mapper) {
    this.profileService = profileService;
    this.mapper = mapper;
  }

  @Operation(summary = "Get all profiles")
  @GetMapping
  public Page<ProfileResource> getAllProfiles(Pageable pageable) {
    return mapper.modelListPage(profileService.getAll(), pageable);
  }

  @Operation(summary = "Get profile by id")
  @GetMapping("{profileId}")
  public ProfileResource getProfileById(@PathVariable Long profileId) {
    return mapper.toResource(profileService.getById(profileId));
  }
  @GetMapping("name/{profileLastName}")
  public ProfileResource getProfileByLastName(@PathVariable String profileLastName) {
    return mapper.toResource(profileService.getByLastName(profileLastName));
  }
  @GetMapping("role/{roleId}")
  public Page<ProfileResource> getAllProfilesByRoleId(@PathVariable Long roleId,Pageable pageable) {
    return mapper.modelListPage(profileService.getAllByRoleId(roleId), pageable);
  }
  @Operation(summary = "Create profile", responses = {
    @ApiResponse(
      description = "Profile successfully created",
      responseCode = "201",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ProfileResource.class)
      )
    )
  })
  @PostMapping
  public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
    return new ResponseEntity<>(mapper.toResource(profileService.create(mapper.toModel(resource))), HttpStatus.CREATED);
  }

  @Operation(summary = "Update profile", responses = {
    @ApiResponse(
      responseCode = "200",
      description = "Profile successfully updated",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ProfileResource.class)
      )
    ),
    @ApiResponse(
      responseCode = "404",
      description = "Profile not found"
    )
  })
  @PutMapping("{profileId}")
  public ProfileResource updateProfile(@PathVariable Long profileId, @RequestBody UpdateProfileResource resource) {
    return mapper.toResource(profileService.update(profileId, mapper.toModel(resource)));
  }

  @Operation(summary = "Delete profile", responses = {
    @ApiResponse(
      responseCode = "204",
      description = "Profile successfully deleted"
    ),
    @ApiResponse(
      responseCode = "404",
      description = "Profile not found"
    )
  })
  @DeleteMapping("{profileId}")
  public ResponseEntity<?> deleteStudent(@PathVariable Long profileId) {
    return profileService.delete(profileId);
  }
}
