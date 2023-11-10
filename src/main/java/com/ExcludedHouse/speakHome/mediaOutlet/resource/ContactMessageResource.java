package com.ExcludedHouse.speakHome.mediaOutlet.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageResource {
    private Long id;
    private ContactResource contact;
    private MessageResource message;
}
