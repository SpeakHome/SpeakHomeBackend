package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactResource {
    @NotNull
    private Long profileId;

    @NotNull
    private Long contactProfileId;

    @NotNull
    private Boolean devicePermission;
}
