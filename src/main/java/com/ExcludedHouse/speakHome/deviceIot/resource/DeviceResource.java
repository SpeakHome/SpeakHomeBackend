package com.ExcludedHouse.speakHome.deviceIot.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResource {
    private Long id;
    private String name;
    //private String type;
    //private String data;//json en txt
    private String baseUrl;
    //private String lastUpdate;
    //private Boolean isOnline;
    private String description;
    private String pictureUrl;
    private LocationResource location;
    private DeviceStatusResource deviceStatus;
}
