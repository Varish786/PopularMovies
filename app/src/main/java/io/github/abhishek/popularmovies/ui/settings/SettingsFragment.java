package io.github.abhishek.popularmovies.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import io.github.abhishek.popularmovies.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}
