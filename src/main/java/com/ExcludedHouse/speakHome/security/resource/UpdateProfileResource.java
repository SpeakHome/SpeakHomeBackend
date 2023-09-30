package com.ExcludedHouse.speakHome.security.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileResource {
  private Long id;

  @NotNull
  @NotBlank
  private String userName;

  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;
}
