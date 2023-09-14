package com.ExcludedHouse.speakHome.relation.api.rest;

import com.ExcludedHouse.speakHome.relation.domain.service.SupportService;
import com.ExcludedHouse.speakHome.relation.mapping.SupportMapper;
import com.ExcludedHouse.speakHome.relation.resource.CreateSupportResource;
import com.ExcludedHouse.speakHome.relation.resource.SupportResource;
import com.ExcludedHouse.speakHome.relation.resource.UpdateSupportResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/supports", produces = "application/json")
public class SupportController {
    private final SupportService supportService;

    private final SupportMapper mapper;

    public SupportController(SupportService supportService, SupportMapper mapper) {
        this.supportService = supportService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<SupportResource> getAllSupports(Pageable pageable) {
        return mapper.modelListPage(supportService.getAll(), pageable);
    }

    @GetMapping("{supportId}")
    public SupportResource getSupportById(@PathVariable Long supportId) {
        return mapper.toResource(supportService.getById(supportId));
    }

    @PostMapping
    public SupportResource createSupport(@RequestBody CreateSupportResource resource) {
        return mapper.toResource(supportService.create(mapper.toModel(resource)));
    }

    @PutMapping("{supportId}")
    public SupportResource updateSupport(@PathVariable Long supportId, @RequestBody UpdateSupportResource resource) {
        return mapper.toResource(supportService.update(supportId, mapper.toModel(resource)));
    }

    @DeleteMapping("{supportId}")
    public ResponseEntity<?> deleteSupport(@PathVariable Long supportId) {
        return supportService.delete(supportId);
    }
}
