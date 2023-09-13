package com.ExcludedHouse.speakHome.chat.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class MessageResource {
    private Long id;
    private String content;
    private String createdAt;
    private ChatResource chat;
}
