package com.example.video_service.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String url;

    private String description;

    private LocalDate datePublication;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;
}
