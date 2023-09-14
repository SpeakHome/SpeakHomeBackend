package com.ExcludedHouse.speakHome.relation.mapping;

import com.ExcludedHouse.speakHome.chat.mapping.ChatMapper;
import com.ExcludedHouse.speakHome.chat.mapping.MessageMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("relationMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public FriendShipMapper friendShipMapper() {
        return new FriendShipMapper();
    }

    @Bean
    public SupportMapper supportMapper() {
        return new SupportMapper();
    }
}
