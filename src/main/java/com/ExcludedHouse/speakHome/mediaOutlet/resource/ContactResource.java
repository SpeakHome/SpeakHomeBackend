package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Message;
import com.ExcludedHouse.speakHome.security.resource.ProfileResource;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ContactResource {
    private Long id;
    private Long profileId;
    private ProfileResource contactProfile;
    private Boolean devicePermission;
}
