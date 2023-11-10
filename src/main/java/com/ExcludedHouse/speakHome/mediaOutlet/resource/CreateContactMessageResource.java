package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactMessageResource {
    @NotNull
    private Long contactId;
    @NotNull
    private Long messageId;
}
