package com.ExcludedHouse.speakHome.profiling.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResource {
  private Long id;
  private String firstName;
  private String lastName;
  private RoleResource role;
}
