package com.ExcludedHouse.speakHome.relation.api.rest;

import com.ExcludedHouse.speakHome.relation.domain.service.FriendShipService;
import com.ExcludedHouse.speakHome.relation.mapping.FriendShipMapper;
import com.ExcludedHouse.speakHome.relation.resource.CreateFriendShipResource;
import com.ExcludedHouse.speakHome.relation.resource.FriendShipResource;
import com.ExcludedHouse.speakHome.relation.resource.UpdateFriendShipResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api/v1/friend_ships", produces = "application/json")
public class FriendShipController {
    private final FriendShipService friendShipService;

    private final FriendShipMapper mapper;

    public FriendShipController(FriendShipService friendShipService, FriendShipMapper mapper) {
        this.friendShipService = friendShipService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<FriendShipResource> getAllFriendShips(Pageable pageable) {
        return mapper.modelListPage(friendShipService.getAll(), pageable);
    }

    @GetMapping("{friendShipId}")
    public FriendShipResource getFriendShipById(@PathVariable Long friendShipId) {
        return mapper.toResource(friendShipService.getById(friendShipId));
    }

    @PostMapping
    public FriendShipResource createFriendShip(@RequestBody CreateFriendShipResource resource) {
        return mapper.toResource(friendShipService.create(mapper.toModel(resource)));
    }

    @PutMapping("{friendShipId}")
    public FriendShipResource updateFriendShip(@PathVariable Long friendShipId, @RequestBody UpdateFriendShipResource resource) {
        return mapper.toResource(friendShipService.update(friendShipId, mapper.toModel(resource)));
    }

    @DeleteMapping("{friendShipId}")
    public ResponseEntity<?> deleteFriendShip(@PathVariable Long friendShipId) {
        return friendShipService.delete(friendShipId);
    }
}
