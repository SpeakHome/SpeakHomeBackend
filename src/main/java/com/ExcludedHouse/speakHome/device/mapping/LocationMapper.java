package com.ExcludedHouse.speakHome.device.mapping;

import com.ExcludedHouse.speakHome.device.domain.model.Location;
import com.ExcludedHouse.speakHome.device.resource.CreateLocationResource;
import com.ExcludedHouse.speakHome.device.resource.LocationResource;
import com.ExcludedHouse.speakHome.device.resource.UpdateLocationResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class LocationMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public LocationResource toResource(Location model) {
        return mapper.map(model, LocationResource.class);
    }

    public Location toModel(CreateLocationResource resource) {
        return mapper.map(resource, Location.class);
    }

    public Location toModel(UpdateLocationResource resource) {
        return mapper.map(resource, Location.class);
    }

    public Page<LocationResource> modelListPage(List<Location> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, LocationResource.class), pageable, modelList.size());
    }
}
