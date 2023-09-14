package com.ExcludedHouse.speakHome.device.mapping;

import com.ExcludedHouse.speakHome.device.domain.model.Device;
import com.ExcludedHouse.speakHome.device.resource.CreateDeviceResource;
import com.ExcludedHouse.speakHome.device.resource.DeviceResource;
import com.ExcludedHouse.speakHome.device.resource.UpdateDeviceResource;
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

    public DeviceResource toResource(Device model) {
        return mapper.map(model, DeviceResource.class);
    }

    public Device toModel(CreateDeviceResource resource) {
        return mapper.map(resource, Device.class);
    }

    public Device toModel(UpdateDeviceResource resource) {
        return mapper.map(resource, Device.class);
    }

    public Page<DeviceResource> modelListPage(List<Device> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DeviceResource.class), pageable, modelList.size());
    }
}