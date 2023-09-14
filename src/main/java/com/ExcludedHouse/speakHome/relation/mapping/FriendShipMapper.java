package com.ExcludedHouse.speakHome.relation.mapping;

import com.ExcludedHouse.speakHome.relation.domain.model.FriendShip;
import com.ExcludedHouse.speakHome.relation.resource.CreateFriendShipResource;
import com.ExcludedHouse.speakHome.relation.resource.FriendShipResource;
import com.ExcludedHouse.speakHome.relation.resource.UpdateFriendShipResource;
import com.ExcludedHouse.speakHome.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FriendShipMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public FriendShipResource toResource(FriendShip model) {
        return mapper.map(model, FriendShipResource.class);
    }

    public FriendShip toModel(CreateFriendShipResource resource) {
        /*return mapper.map(resource, Chat.class);*/
        FriendShip friendShip = new FriendShip();
        friendShip.setProfile1Id(resource.getProfile1Id());
        friendShip.setProfile2Id(resource.getProfile2Id());
        friendShip.setDevicePermission(resource.getDevicePermission());
        return friendShip;
    }

    public FriendShip toModel(UpdateFriendShipResource resource) {
        return mapper.map(resource, FriendShip.class);
    }

    public Page<FriendShipResource> modelListPage(List<FriendShip> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FriendShipResource.class), pageable, modelList.size());
    }
}