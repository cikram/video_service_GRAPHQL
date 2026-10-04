package com.example.video_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorRequest creator;
}