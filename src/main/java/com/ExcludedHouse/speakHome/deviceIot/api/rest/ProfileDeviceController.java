package com.ExcludedHouse.speakHome.deviceIot.api.rest;

import com.ExcludedHouse.speakHome.deviceIot.domain.service.ProfileDeviceService;
import com.ExcludedHouse.speakHome.deviceIot.mapping.ProfileDeviceMapper;
import com.ExcludedHouse.speakHome.deviceIot.resource.CreateProfileDeviceResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.ProfileDeviceResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.UpdateProfileDeviceResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/device-iot/profile-devices", produces = "application/json")
public class ProfileDeviceController {
    private final ProfileDeviceService profileDeviceService;

    private final ProfileDeviceMapper mapper;

    public ProfileDeviceController(ProfileDeviceService profileDeviceService, ProfileDeviceMapper mapper) {
        this.profileDeviceService = profileDeviceService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<ProfileDeviceResource> getAllProfileDevices(Pageable pageable) {
        return mapper.modelListPage(profileDeviceService.getAll(), pageable);
    }

    @GetMapping("{profileDeviceId}")
    public ProfileDeviceResource getProfileDeviceById(@PathVariable Long profileDeviceId) {
        return mapper.toResource(profileDeviceService.getById(profileDeviceId));
    }

    @PostMapping
    public ProfileDeviceResource createProfileDevice(@RequestBody CreateProfileDeviceResource resource) {
        return mapper.toResource(profileDeviceService.create(mapper.toModel(resource)));
    }

    @PutMapping("{profileDeviceId}")
    public ProfileDeviceResource updateProfileDevice(@PathVariable Long profileDeviceId, @RequestBody UpdateProfileDeviceResource resource) {
        return mapper.toResource(profileDeviceService.update(profileDeviceId, mapper.toModel(resource)));
    }

    @DeleteMapping("{profileDeviceId}")
    public ResponseEntity<?> deleteProfileDevice(@PathVariable Long profileDeviceId) {
        return profileDeviceService.delete(profileDeviceId);
    }
}
