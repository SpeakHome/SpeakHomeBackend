package com.ExcludedHouse.speakHome.device.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileDeviceResource {
    @NotNull
    private Long profileId;
    @NotNull
    private Long deviceId;
}
