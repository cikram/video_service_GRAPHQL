package com.example.video_service.service;

import com.example.video_service.dao.entities.Video;
import com.example.video_service.dto.VideoRequest;

import java.util.List;

public interface VideoManager {
    List<Video> findAll();
    Video findById(Long id);
    Video saveVideo(VideoRequest videoRequest);
    Video updateVideo(Video video);
}