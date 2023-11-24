package com.ExcludedHouse.speakHome.deviceIot.mapping;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.Location;
import com.ExcludedHouse.speakHome.deviceIot.resource.CreateLocationResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.LocationResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.UpdateLocationResource;
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
        Location location=new Location();
        location.setName(resource.getName());
        return location;
    }

    public Location toModel(UpdateLocationResource resource) {
        return mapper.map(resource, Location.class);
    }

    public Page<LocationResource> modelListPage(List<Location> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, LocationResource.class), pageable, modelList.size());
    }
}
