package com.ExcludedHouse.speakHome.deviceIot.mapping;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.DeviceStatus;
import com.ExcludedHouse.speakHome.deviceIot.resource.CreateDeviceStatusResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.DeviceStatusResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.UpdateDeviceStatusResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DeviceStatusMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public DeviceStatusResource toResource(DeviceStatus model) {
        return mapper.map(model, DeviceStatusResource.class);
    }

    public DeviceStatus toModel(CreateDeviceStatusResource resource) {
        DeviceStatus deviceStatus=new DeviceStatus();
        deviceStatus.setName(resource.getName());
        return deviceStatus;
    }

    public DeviceStatus toModel(UpdateDeviceStatusResource resource) {
        return mapper.map(resource, DeviceStatus.class);
    }

    public Page<DeviceStatusResource> modelListPage(List<DeviceStatus> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DeviceStatusResource.class), pageable, modelList.size());
    }
}