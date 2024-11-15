package com.minnthitoo.spring_jpa.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private Long id;

    @NotBlank(message = "{required.movie.title}")
    @Size(min = 3, max = 100, message = "{size.movie.title}")
    private String title;

    @NotNull(message = "{required.movie.year}")
    private Integer year;

    @NotBlank(message = "{required.movie.genre}")
    @Size(min = 3, max = 100, message = "{size.movie.genre}")
    private String genre;

    @NotNull(message = "{required.movie.movieDetails}")
    MovieDetailDto movieDetails;

    List<ActorDto> actors;
    List<DirectorDto> directors;
    List<CommentDto> comments;
}
