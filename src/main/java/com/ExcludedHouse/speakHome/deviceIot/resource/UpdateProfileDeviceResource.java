package com.ExcludedHouse.speakHome.deviceIot.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDeviceResource {
    private Long id;
    @NotNull
    private Long profileId;
}
