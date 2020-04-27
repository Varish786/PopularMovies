package io.github.abhishek.popularmovies.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.github.abhishek.popularmovies.R;
import io.github.abhishek.popularmovies.databinding.ActivityMainBinding;
import io.github.abhishek.popularmovies.ui.movies.MovieFragment;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    public static final String FRAG_TAG_POPULAR = "frag-popular";
    public static final String FRAG_TAG_TOP_RATED = "frag-top-rated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.plant(new Timber.DebugTree());
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        loadPopularMoviesFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popular:
                loadPopularMoviesFragment();
                return true;
            case R.id.action_top_rated:
                loadTopRatedMoviesFragment();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void loadPopularMoviesFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_POPULAR);
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_POPULAR).commit();
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_TOP_RATED);
    }

    private void loadTopRatedMoviesFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_TOP_RATED);
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_TOP_RATED).commit();
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_POPULAR);
    }

    private void hideFragments(String... tags) {
        for (String tag : tags) {
            fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }
}
