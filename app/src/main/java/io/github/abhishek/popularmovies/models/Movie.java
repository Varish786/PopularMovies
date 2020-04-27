package io.github.abhishek.popularmovies.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Movie {
    @SerializedName("title") String title;
    @SerializedName("vote_average") float votes;
    @SerializedName("poster_path") String posterPath;
}
