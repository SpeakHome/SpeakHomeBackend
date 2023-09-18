package com.ExcludedHouse.speakHome.deviceIot.domain.model;

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
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String type;

    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String data;//json en txt

    @NotNull
    @NotBlank
    @Size(max = 32)
    private String lastUpdate;

    @NotNull
    private Boolean isOnline;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonIgnore
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "device_status_id", nullable = false)
    @JsonIgnore
    private DeviceStatus deviceStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "device")
    private Set<ProfileDevice> profileDevices;
}
