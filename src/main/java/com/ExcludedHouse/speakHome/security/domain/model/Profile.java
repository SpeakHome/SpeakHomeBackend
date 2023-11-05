package com.ExcludedHouse.speakHome.security.domain.model;

import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Contact;
import com.ExcludedHouse.speakHome.mediaOutlet.domain.model.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  // Relationships

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "role_id", nullable = false)
  @JsonIgnore
  private Role role;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "contactProfile")
  private Set<Contact> contacts;
}
