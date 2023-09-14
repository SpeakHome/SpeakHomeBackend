package com.ExcludedHouse.speakHome.relation.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFriendShipResource {
    private Long id;

    @NotNull
    private Boolean devicePermission;

    @NotNull
    private Long profile1Id;

    @NotNull
    private Long profile2Id;
}
