package com.ExcludedHouse.speakHome.device.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("deviceMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public DeviceMapper deviceMapper() {
        return new DeviceMapper();
    }

    @Bean
    public DeviceStatusMapper deviceStatusMapper() {
        return new DeviceStatusMapper();
    }

    @Bean
    public LocationMapper locationMapper() {
        return new LocationMapper();
    }

    @Bean
    public ProfileDeviceMapper profileDeviceMapper() {
        return new ProfileDeviceMapper();
    }
}
