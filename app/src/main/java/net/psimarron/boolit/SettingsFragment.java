package net.psimarron.boolit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

// Pflegt die Einstellungen der Anwendung.
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Konfiguration der Einstellungen laden
        addPreferencesFromResource(R.xml.preferences);

        // Beschreibungen setzen
        updateSummaries();
    }

    // Aktualisiert alle Beschreibungen.
    public void updateSummaries() {
        // Ermittelt die aktuelle Auswahl
        Activity activity = getActivity();
        String[] values = activity.getResources().getStringArray(R.array.pref_icon_mode_values);
        String iconModeName = WelcomeActivity.getIconModeSettingName(activity);
        String iconMode = PreferenceManager.getDefaultSharedPreferences(activity).getString(iconModeName, values[0]);

        // Ermittelt den zugehörigen Anzeigetext
        for (int i = 0; i < values.length; i++)
            if (values[i].equals(iconMode)) {
                String[] entries = activity.getResources().getStringArray(R.array.pref_icon_mode_entries);

                ListPreference level = (ListPreference) findPreference(iconModeName);
                level.setSummary(entries[i]);

                break;
            }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Wir sind nicht mehr aktiv
        Activity activity = getActivity();
        if (activity == null)
            return;

        // Neuladen des Spiels erzwingen - im Moment ist das in Ordnung, später wollen wir vielleicht etwas genauer hinschauen
        activity.setResult(Activity.RESULT_OK);

        // Anzeige aktualisieren
        updateSummaries();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Änderungen aktualisieren die Anzeige
        PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Wird sind nun nicht mehr an Informationen über Änderungen interessiert
        PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
    }
}
