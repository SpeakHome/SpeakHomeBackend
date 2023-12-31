package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessageResource {
    private Long id;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    @NotBlank
    private Date createdAt;
}
