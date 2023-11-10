package com.ExcludedHouse.speakHome.mediaOutlet.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("chatMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public MessageMapper messageMapper() {
        return new MessageMapper();
    }

    @Bean
    public ContactMapper contactMapper() {
        return new ContactMapper();
    }

    @Bean
    public ContactMessageMapper contactMessageMapper() {
        return new ContactMessageMapper();
    }
}
