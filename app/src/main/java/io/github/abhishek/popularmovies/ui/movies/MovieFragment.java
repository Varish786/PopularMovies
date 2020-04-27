package io.github.abhishek.popularmovies.ui.movies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import io.github.abhishek.popularmovies.R;
import io.github.abhishek.popularmovies.adapters.MovieAdapter;
import io.github.abhishek.popularmovies.databinding.FragmentMovieBinding;
import io.github.abhishek.popularmovies.network.TMDbClient;
import io.github.abhishek.popularmovies.ui.MainActivity;

public class MovieFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private FragmentMovieBinding binding;
    private String tag;
    private boolean displayedMovies = false;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        tag = getTag();
        PreferenceManager.getDefaultSharedPreferences(requireContext()).registerOnSharedPreferenceChangeListener(this);
        displayMovies();
        return binding.getRoot();
    }

    private void displayMovies() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String apiKey = sharedPreferences.getString(getString(R.string.pref_key_api_key), null);
        if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(apiKey)) {
            switch (tag) {
                case MainActivity.FRAG_TAG_POPULAR:
                    TMDbClient.getInstance().getPopularMovies(apiKey).observe(getViewLifecycleOwner(), movies -> {
                        if (movies != null) {
                            displayedMovies = true;
                            binding.groupMessage.setVisibility(View.GONE);
                            MovieAdapter adapter = new MovieAdapter(movies);
                            binding.rvMovie.setAdapter(adapter);
                        }
                    });
                    break;
                case MainActivity.FRAG_TAG_TOP_RATED:
                    TMDbClient.getInstance().getTopRatedMovies(apiKey).observe(getViewLifecycleOwner(), movies -> {
                        if (movies != null) {
                            displayedMovies = true;
                            binding.groupMessage.setVisibility(View.GONE);
                            MovieAdapter adapter = new MovieAdapter(movies);
                            binding.rvMovie.setAdapter(adapter);
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!displayedMovies) {
            displayMovies();
        }
    }
}
