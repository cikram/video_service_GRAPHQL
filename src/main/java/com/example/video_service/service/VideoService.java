package com.example.video_service.service;

import com.example.video_service.dao.entities.Video;
import com.example.video_service.dao.repositories.CreatorRepository;
import com.example.video_service.dao.repositories.VideoRepository;
import com.example.video_service.dto.VideoRequest;
import com.example.video_service.mappers.VideoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService implements VideoManager {

    private final VideoRepository videoRepository;
    private final CreatorRepository creatorRepository;
    private final VideoMapper videoMapper;

    public VideoService(VideoRepository videoRepository,
                        CreatorRepository creatorRepository,
                        VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.creatorRepository = creatorRepository;
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Video findById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Video %s not found", id)));
    }

    @Override
    public Video saveVideo(VideoRequest videoRequest) {
        Video video = videoMapper.fromVideoRequest(videoRequest);
        creatorRepository.save(video.getCreator());
        return videoRepository.save(video);
    }

    @Override
    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }
}