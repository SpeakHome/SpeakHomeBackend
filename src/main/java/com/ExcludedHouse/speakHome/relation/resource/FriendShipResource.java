package com.ExcludedHouse.speakHome.relation.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class FriendShipResource {
    private Long id;
    private Boolean devicePermission;
    private Long profile1Id;
    private Long profile2Id;
}
