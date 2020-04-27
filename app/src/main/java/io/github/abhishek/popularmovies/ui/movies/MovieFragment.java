package io.github.abhishek.popularmovies.ui.movies;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import io.github.abhishek.popularmovies.R;
import io.github.abhishek.popularmovies.adapters.MovieAdapter;
import io.github.abhishek.popularmovies.network.TMDbClient;
import io.github.abhishek.popularmovies.ui.MainActivity;

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
        String tag = getTag();
        if (!TextUtils.isEmpty(tag)) {
            switch (tag) {
                case MainActivity.FRAG_TAG_POPULAR:
                    TMDbClient.getInstance()
                            .getPopularMovies("77a50bcabf04a05e912c992d4dfb6e00")
                            .observe(getViewLifecycleOwner(), movies -> {
                                if (movies != null) {
                                    MovieAdapter adapter = new MovieAdapter(movies);
                                    RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                    break;
                case MainActivity.FRAG_TAG_TOP_RATED:
                    TMDbClient.getInstance()
                            .getTopRatedMovies("77a50bcabf04a05e912c992d4dfb6e00")
                            .observe(getViewLifecycleOwner(), movies -> {
                                if (movies != null) {
                                    MovieAdapter adapter = new MovieAdapter(movies);
                                    RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                    break;
            }
        }
        return view;
    }
}
