package com.example.video_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorRequest {
    private String name;
    private String email;
}