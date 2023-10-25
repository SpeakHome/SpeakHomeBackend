package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ChatResource {
    private Long id;
    private Long profile1Id;
    private Long profile2Id;
    private Boolean devicePermission;
}
