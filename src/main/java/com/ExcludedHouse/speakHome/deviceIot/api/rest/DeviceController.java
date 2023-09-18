package com.ExcludedHouse.speakHome.deviceIot.api.rest;

import com.ExcludedHouse.speakHome.deviceIot.domain.service.DeviceService;
import com.ExcludedHouse.speakHome.deviceIot.mapping.DeviceMapper;
import com.ExcludedHouse.speakHome.deviceIot.resource.CreateDeviceResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.DeviceResource;
import com.ExcludedHouse.speakHome.deviceIot.resource.UpdateDeviceResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/device-iot/devices", produces = "application/json")
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceMapper mapper;

    public DeviceController(DeviceService deviceService, DeviceMapper mapper) {
        this.deviceService = deviceService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<DeviceResource> getAllDevices(Pageable pageable) {
        return mapper.modelListPage(deviceService.getAll(), pageable);
    }

    @GetMapping("{deviceId}")
    public DeviceResource getDeviceById(@PathVariable Long deviceId) {
        return mapper.toResource(deviceService.getById(deviceId));
    }

    @PostMapping
    public DeviceResource createDevice(@RequestBody CreateDeviceResource resource) {
        return mapper.toResource(deviceService.create(mapper.toModel(resource)));
    }

    @PutMapping("{deviceId}")
    public DeviceResource updateDevice(@PathVariable Long deviceId, @RequestBody UpdateDeviceResource resource) {
        return mapper.toResource(deviceService.update(deviceId, mapper.toModel(resource)));
    }

    @DeleteMapping("{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long deviceId) {
        return deviceService.delete(deviceId);
    }
}
