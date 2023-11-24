package com.ExcludedHouse.speakHome.deviceIot.mapping;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Device;
import com.ExcludedHouse.speakHome.deviceIot.domain.service.DeviceStatusService;
import com.ExcludedHouse.speakHome.deviceIot.domain.service.LocationService;
import com.ExcludedHouse.speakHome.deviceIot.resource.CreateDeviceResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.DeviceResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.UpdateDeviceResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DeviceMapper  implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private DeviceStatusService deviceStatusService;

    public DeviceResource toResource(Device model) {
        return mapper.map(model, DeviceResource.class);
    }

    public Device toModel(CreateDeviceResource resource) {
        Device device=new Device();
        device.setName(resource.getName());
        device.setBaseUrl(resource.getBaseUrl());
        device.setDescription(resource.getDescription());
        device.setPictureUrl(resource.getPictureUrl());
        device.setLocation(locationService.getById(resource.getLocationId()));
        device.setDeviceStatus(deviceStatusService.getById(resource.getDeviceStatusId()));
        return device;
    }

    public Device toModel(UpdateDeviceResource resource) {
        return mapper.map(resource, Device.class);
    }

    public Page<DeviceResource> modelListPage(List<Device> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DeviceResource.class), pageable, modelList.size());
    }
}