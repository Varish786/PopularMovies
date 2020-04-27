package io.github.abhishek.popularmovies.network;

import io.github.abhishek.popularmovies.models.MovieNetworkResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface TMDbAPI {
    @GET("movie/popular")
    Call<MovieNetworkResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieNetworkResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
