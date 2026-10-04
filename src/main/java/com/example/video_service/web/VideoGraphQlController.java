package com.example.video_service.web;

import com.example.video_service.dao.entities.Creator;
import com.example.video_service.dao.entities.Video;
import com.example.video_service.dto.CreatorRequest;
import com.example.video_service.dto.VideoRequest;
import com.example.video_service.service.CreatorManager;
import com.example.video_service.service.VideoManager;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {

    private final CreatorManager creatorManager;
    private final VideoManager videoManager;

    public VideoGraphQlController(CreatorManager creatorManager, VideoManager videoManager) {
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
    }

    @QueryMapping
    public List<Video> videoList() {
        return videoManager.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorManager.findById(id);
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator) {
        return creatorManager.saveCreator(creator);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video) {
        return videoManager.saveVideo(video);
    }

    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    CreatorRequest creatorRequest = CreatorRequest.builder()
                            .name("x" + new Random().nextInt())
                            .email("x@gmail.com")
                            .build();
                    Creator creator = creatorManager.saveCreator(creatorRequest);
                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);
                    return video;
                })
        );
    }
}