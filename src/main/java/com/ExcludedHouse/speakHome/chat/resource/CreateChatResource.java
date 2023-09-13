package com.ExcludedHouse.speakHome.chat.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatResource {
    @NotNull
    private Long profile1Id;

    @NotNull
    private Long profile2Id;
}
