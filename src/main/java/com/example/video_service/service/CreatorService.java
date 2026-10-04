package com.example.video_service.service;

import com.example.video_service.dao.entities.Creator;
import com.example.video_service.dao.repositories.CreatorRepository;
import com.example.video_service.dto.CreatorRequest;
import com.example.video_service.mappers.CreatorMapper;
import org.springframework.stereotype.Service;

@Service
public class CreatorService implements CreatorManager {

    private final CreatorRepository creatorRepository;
    private final CreatorMapper creatorMapper;

    public CreatorService(CreatorRepository creatorRepository, CreatorMapper creatorMapper) {
        this.creatorRepository = creatorRepository;
        this.creatorMapper = creatorMapper;
    }

    @Override
    public Creator saveCreator(CreatorRequest creatorRequest) {
        return creatorRepository.save(creatorMapper.fromCreatorRequest(creatorRequest));
    }

    @Override
    public Creator findById(Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Creator %s not found", id)));
    }
}