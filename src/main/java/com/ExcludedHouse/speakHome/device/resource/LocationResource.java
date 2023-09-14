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
public class LocationResource {
    private Long id;
    private String name;
    private String description;
}
