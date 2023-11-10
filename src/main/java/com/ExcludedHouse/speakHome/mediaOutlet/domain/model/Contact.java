package com.ExcludedHouse.speakHome.mediaOutlet.domain.model;

import com.ExcludedHouse.speakHome.security.domain.model.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long profileId;

    @NotNull
    private Boolean devicePermission;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "contact_profile_id", nullable = false)
    @JsonIgnore
    private Profile contactProfile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "contact")
    private Set<ContactMessage> contactMessages;
}
