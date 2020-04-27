package io.github.abhishek.popularmovies.models;

import lombok.Data;

@Data
public class Movie {
    private final String title;
    private final float votes;
}
