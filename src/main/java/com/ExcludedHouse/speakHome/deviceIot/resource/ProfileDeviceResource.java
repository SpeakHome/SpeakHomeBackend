package com.ExcludedHouse.speakHome.deviceIot.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDeviceResource {
    private Long id;
    private Long profileId;
    private DeviceResource device;
}
