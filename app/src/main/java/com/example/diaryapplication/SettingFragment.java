package com.example.diaryapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.diaryapplication.R;
import com.example.diaryapplication.TimePickerFragment;

public class SettingFragment extends PreferenceFragment {

    SharedPreferences prefs;

    ListPreference themePreference;
    SwitchPreference alarmPreference;
    Preference alarmTime;
    int hour1, minute1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preference);
        themePreference = (ListPreference) findPreference("theme");
        alarmPreference = (SwitchPreference) findPreference("alarm");
        alarmTime = (PreferenceScreen) findPreference("al");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        private void setTheme () {

            if (themePreference.getString("theme", "테마 1").equals("th1")) {

                setTheme(R.style.AppTheme1);
            } else {
                setTheme(R.style.AppTheme2);
            }


            prefs.registerOnSharedPreferenceChangeListener(prefListener);


            SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (key.equals("theme")) {
                        themePreference.setSummary(prefs.getString("theme", "테마 1"));
                    }
                    //if (key.equals("alarm")) {


                    //}

                    if (key.equals("al")) {
                        Intent intent = null;
                        intent = new Intent(getContext(), TimePickerFragment.class);
                        startActivity(intent);
                    }

                    ((BaseAdapter) getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();


                }


            };
        }
    }

}
