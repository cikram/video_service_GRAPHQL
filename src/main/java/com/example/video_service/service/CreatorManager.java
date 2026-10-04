package com.example.video_service.service;

import com.example.video_service.dao.entities.Creator;
import com.example.video_service.dto.CreatorRequest;

public interface CreatorManager {
    Creator saveCreator(CreatorRequest creatorRequest);
    Creator findById(Long id);
}