package com.example.video_service.mappers;

import com.example.video_service.dao.entities.Creator;
import com.example.video_service.dto.CreatorRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {

    private final ModelMapper modelMapper;

    public CreatorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Creator fromCreatorRequest(CreatorRequest request) {
        return modelMapper.map(request, Creator.class);
    }
}
