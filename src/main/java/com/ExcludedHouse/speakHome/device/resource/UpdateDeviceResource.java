package com.ExcludedHouse.speakHome.device.resource;

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
    private Long id;
    @NotNull
    @NotBlank
    @Size(max = 32)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String type;

    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String data;//json en txt

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String lastUpdate;

    @NotNull
    private Boolean isOnline;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String description;
}
