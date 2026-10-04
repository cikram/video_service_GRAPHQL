package com.example.video_service.mappers;

import com.example.video_service.dao.entities.Video;
import com.example.video_service.dto.VideoRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    private final ModelMapper modelMapper;

    public VideoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Video fromVideoRequest(VideoRequest request) {
        return modelMapper.map(request, Video.class);
    }
}