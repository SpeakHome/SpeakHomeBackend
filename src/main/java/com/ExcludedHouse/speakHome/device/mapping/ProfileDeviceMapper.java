package com.ExcludedHouse.speakHome.device.mapping;

import com.ExcludedHouse.speakHome.device.domain.model.Device;
import com.ExcludedHouse.speakHome.device.domain.model.ProfileDevice;
import com.ExcludedHouse.speakHome.device.domain.service.DeviceService;
import com.ExcludedHouse.speakHome.device.resource.CreateProfileDeviceResource;
import com.ExcludedHouse.speakHome.device.resource.ProfileDeviceResource;
import com.ExcludedHouse.speakHome.device.resource.UpdateProfileDeviceResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProfileDeviceMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;
    @Autowired
    private DeviceService deviceService;
    public ProfileDeviceResource toResource(ProfileDevice model) {
        return mapper.map(model, ProfileDeviceResource.class);
    }

    public ProfileDevice toModel(CreateProfileDeviceResource resource) {
        /*return mapper.map(resource, ProfileDevice.class);*/
        ProfileDevice profileDevice = new ProfileDevice();
        profileDevice.setProfileId(resource.getProfileId());
        Device device = deviceService.getById(resource.getDeviceId());
        profileDevice.setDevice(device);
        return profileDevice;
    }

    public ProfileDevice toModel(UpdateProfileDeviceResource resource) {
        return mapper.map(resource, ProfileDevice.class);
    }

    public Page<ProfileDeviceResource> modelListPage(List<ProfileDevice> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ProfileDeviceResource.class), pageable, modelList.size());
    }
}