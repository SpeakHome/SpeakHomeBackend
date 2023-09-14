package com.ExcludedHouse.speakHome.relation.domain.service;

import com.ExcludedHouse.speakHome.relation.domain.model.FriendShip;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FriendShipService {
    List<FriendShip> getAll();
    FriendShip getById(Long friendShipId);
    FriendShip create(FriendShip friendShip);
    FriendShip update(Long friendShipId, FriendShip friendShip);
    ResponseEntity<?> delete(Long friendShipId);
}
