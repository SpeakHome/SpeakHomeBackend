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
public class UpdateLocationResource {
    private Long id;
    @NotNull
    @NotBlank
    @Size(max = 32)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String description;
}
