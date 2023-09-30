package com.ExcludedHouse.speakHome.security.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResource {
  private Long id;
  private String userName;
  private String email;
  private String password;
  private RoleResource role;
}
