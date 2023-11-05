package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContactResource {
    private Long id;

    @NotNull
    private Boolean devicePermission;
}
