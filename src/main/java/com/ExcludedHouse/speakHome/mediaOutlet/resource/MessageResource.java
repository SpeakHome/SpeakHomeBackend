package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class MessageResource {
    private Long id;
    private String content;
    private Date createdAt;
    private ContactResource chat;
}
