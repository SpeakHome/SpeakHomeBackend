package com.ExcludedHouse.speakHome.relation.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friend_ships")
public class FriendShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean devicePermission;

    @NotNull
    private Long profile1Id;

    @NotNull
    private Long profile2Id;
}
