package net.learn2develop.menuproject18;


import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class PrefsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//Sve vrednosti su sacuvane u preferences.xml
        addPreferencesFromResource(R.xml.preferences);
    }
}
