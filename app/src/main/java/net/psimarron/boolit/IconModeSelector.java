package net.psimarron.boolit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.ListAdapter;

// Auswahlelement für die Art der Darstellung.
public class IconModeSelector extends ListPreference {
    // Erstellt ein neues Auswahlelement.
    public IconModeSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        CharSequence[] values = getEntryValues();
        Context context = getContext();
        int index = 0;

        // Aktuelle Auswahl ermitteln und in die laufende Position der Auswahlzeile umsetzen
        String iconMode = PreferenceManager.getDefaultSharedPreferences(context).getString(WelcomeActivity.getIconModeSettingName(context), null);
        for (int i = 0; i < values.length; i++)
            if (values[i].toString().equals(iconMode)) {
                index = i;
                break;
            }

        // Eigene Visualisierung erzeugen
        ListAdapter iconModeAdapter = new IconModeAdapter(context, R.layout.icon_mode_selector, values, index, this);

        // Eigene Visualisierung aktivieren
        builder.setAdapter(iconModeAdapter, this);

        super.onPrepareDialogBuilder(builder);
    }

    // Übernimmt die Auswahl des Anwenders.
    public void setResult(String value) {
        // Auswahl blind übernehmen - den Rest macht der PreferencesManager für uns
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        edit.putString(getKey(), value);
        edit.commit();

        getDialog().dismiss();
    }
}