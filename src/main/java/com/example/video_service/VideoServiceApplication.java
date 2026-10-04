package com.example.video_service;

import com.example.video_service.dao.entities.Creator;
import com.example.video_service.dao.entities.Video;
import com.example.video_service.dao.repositories.CreatorRepository;
import com.example.video_service.dao.repositories.VideoRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            CreatorRepository creatorRepository,
            VideoRepository videoRepository
    ) {
        return args -> {

            Creator yassine = Creator.builder()
                    .name("Yassine")
                    .email("yassine@gmail.com")
                    .build();

            Creator salma = Creator.builder()
                    .name("Salma")
                    .email("salma@gmail.com")
                    .build();

            Creator karim = Creator.builder()
                    .name("Karim")
                    .email("karim@gmail.com")
                    .build();

            creatorRepository.saveAll(
                    List.of(yassine, salma, karim)
            );

            Video video1 = Video.builder()
                    .name("Intro Spring Boot")
                    .url("https://youtube.com/v1")
                    .description("Les bases de Spring Boot")
                    .datePublication(LocalDate.of(2026, 1, 10))
                    .creator(yassine)
                    .build();

            Video video2 = Video.builder()
                    .name("Spring Data JPA")
                    .url("https://youtube.com/v2")
                    .description("Relations OneToMany et ManyToOne")
                    .datePublication(LocalDate.of(2026, 2, 15))
                    .creator(salma)
                    .build();

            Video video3 = Video.builder()
                    .name("GraphQL avec Spring")
                    .url("https://youtube.com/v3")
                    .description("Créer une API GraphQL")
                    .datePublication(LocalDate.of(2026, 3, 20))
                    .creator(salma)
                    .build();

            Video video4 = Video.builder()
                    .name("Docker pour débutants")
                    .url("https://youtube.com/v4")
                    .description("Conteneuriser une application")
                    .datePublication(LocalDate.of(2026, 4, 5))
                    .creator(karim)
                    .build();

            videoRepository.saveAll(
                    List.of(video1, video2, video3, video4)
            );
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> stringToDate = ctx ->
                ctx.getSource() == null ? null :
                        LocalDate.parse(
                                ctx.getSource(),
                                DateTimeFormatter.ofPattern("[yyyy-MM-dd][dd/MM/yyyy]")
                        );

        modelMapper.addConverter(stringToDate, String.class, LocalDate.class);
        return modelMapper;
    }
}