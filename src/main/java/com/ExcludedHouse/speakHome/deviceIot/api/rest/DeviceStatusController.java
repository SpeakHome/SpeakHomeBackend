package com.ExcludedHouse.speakHome.deviceIot.api.rest;

import com.ExcludedHouse.speakHome.deviceIot.domain.service.DeviceStatusService;
import com.ExcludedHouse.speakHome.deviceIot.mapping.DeviceStatusMapper;
import com.ExcludedHouse.speakHome.deviceIot.resource.CreateDeviceStatusResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.DeviceStatusResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.UpdateDeviceStatusResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/device-iot/device-statuses", produces = "application/json")
public class DeviceStatusController {
    private final DeviceStatusService deviceStatusService;

    private final DeviceStatusMapper mapper;

    public DeviceStatusController(DeviceStatusService deviceStatusService, DeviceStatusMapper mapper) {
        this.deviceStatusService = deviceStatusService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<DeviceStatusResource> getAllDeviceStatuses(Pageable pageable) {
        return mapper.modelListPage(deviceStatusService.getAll(), pageable);
    }

    @GetMapping("{deviceStatusId}")
    public DeviceStatusResource getDeviceStatusById(@PathVariable Long deviceStatusId) {
        return mapper.toResource(deviceStatusService.getById(deviceStatusId));
    }

    @PostMapping
    public DeviceStatusResource createDeviceStatus(@RequestBody CreateDeviceStatusResource resource) {
        return mapper.toResource(deviceStatusService.create(mapper.toModel(resource)));
    }

    @PutMapping("{deviceStatusId}")
    public DeviceStatusResource updateDeviceStatus(@PathVariable Long deviceStatusId, @RequestBody UpdateDeviceStatusResource resource) {
        return mapper.toResource(deviceStatusService.update(deviceStatusId, mapper.toModel(resource)));
    }

    @DeleteMapping("{deviceStatusId}")
    public ResponseEntity<?> deleteDeviceStatus(@PathVariable Long deviceStatusId) {
        return deviceStatusService.delete(deviceStatusId);
    }
}
