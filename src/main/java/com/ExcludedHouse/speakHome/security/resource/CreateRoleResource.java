package com.ExcludedHouse.speakHome.security.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleResource {
  @NotNull
  @NotBlank
  private String name;
}
