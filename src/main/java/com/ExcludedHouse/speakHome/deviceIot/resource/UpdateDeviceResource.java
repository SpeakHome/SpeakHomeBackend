package com.ExcludedHouse.speakHome.deviceIot.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceResource {

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String name;

    @NotNull
    @NotBlank
    private String baseUrl;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String description;

    @NotNull
    @NotBlank
    private String pictureUrl;
}
