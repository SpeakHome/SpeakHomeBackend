package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChatResource {
    private Long id;

    @NotNull
    private Long profile1Id;

    @NotNull
    private Long profile2Id;

    @NotNull
    private Boolean devicePermission;
}
