package com.ExcludedHouse.speakHome.contact.api.rest;

import com.ExcludedHouse.speakHome.contact.domain.service.FriendShipService;
import com.ExcludedHouse.speakHome.contact.mapping.FriendShipMapper;
import com.ExcludedHouse.speakHome.contact.resource.CreateFriendShipResource;
import com.ExcludedHouse.speakHome.contact.resource.FriendShipResource;
import com.ExcludedHouse.speakHome.contact.resource.UpdateFriendShipResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api/v1/contact/friend_ships", produces = "application/json")
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
