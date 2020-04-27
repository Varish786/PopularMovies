package io.github.abhishek.popularmovies.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.abhishek.popularmovies.R;
import io.github.abhishek.popularmovies.adapters.MovieAdapter;
import io.github.abhishek.popularmovies.models.Movie;

public class MovieFragment extends Fragment {

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        inflateView(view);
        return view;
    }

    private void inflateView(@NonNull View view) {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(getString(R.string.sample_movie_title), Float.parseFloat(getString(R.string.sample_rating))));
        movies.add(new Movie(getString(R.string.sample_movie_title), Float.parseFloat(getString(R.string.sample_rating))));
        movies.add(new Movie(getString(R.string.sample_movie_title), Float.parseFloat(getString(R.string.sample_rating))));
        movies.add(new Movie(getString(R.string.sample_movie_title), Float.parseFloat(getString(R.string.sample_rating))));
        movies.add(new Movie(getString(R.string.sample_movie_title), Float.parseFloat(getString(R.string.sample_rating))));
        MovieAdapter adapter = new MovieAdapter(movies);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
        recyclerView.setAdapter(adapter);
    }
}
