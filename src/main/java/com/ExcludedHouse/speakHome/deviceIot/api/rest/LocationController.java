package com.ExcludedHouse.speakHome.deviceIot.api.rest;

import com.ExcludedHouse.speakHome.deviceIot.domain.service.LocationService;
import com.ExcludedHouse.speakHome.deviceIot.mapping.LocationMapper;
import com.ExcludedHouse.speakHome.deviceIot.resource.CreateLocationResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.LocationResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.UpdateLocationResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/device-iot/locations", produces = "application/json")
public class LocationController {
    private final LocationService locationService;

    private final LocationMapper mapper;

    public LocationController(LocationService locationService, LocationMapper mapper) {
        this.locationService = locationService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<LocationResource> getAllLocations(Pageable pageable) {
        return mapper.modelListPage(locationService.getAll(), pageable);
    }

    @GetMapping("{locationId}")
    public LocationResource getLocationById(@PathVariable Long locationId) {
        return mapper.toResource(locationService.getById(locationId));
    }

    @PostMapping
    public LocationResource createLocation(@RequestBody CreateLocationResource resource) {
        return mapper.toResource(locationService.create(mapper.toModel(resource)));
    }

    @PutMapping("{locationId}")
    public LocationResource updateLocation(@PathVariable Long locationId, @RequestBody UpdateLocationResource resource) {
        return mapper.toResource(locationService.update(locationId, mapper.toModel(resource)));
    }

    @DeleteMapping("{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long locationId) {
        return locationService.delete(locationId);
    }
}
