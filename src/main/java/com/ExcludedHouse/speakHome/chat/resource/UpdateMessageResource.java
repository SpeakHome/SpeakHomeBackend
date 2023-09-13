package com.ExcludedHouse.speakHome.chat.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessageResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String content;

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String createdAt;
}
