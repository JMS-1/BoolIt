package net.psimarron.boolit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

// Bietet zur Einstellung der Anzeige die unterschiedlichen Modi mit einem zusätzlichen Bild als visuelle Hilfe zur Auswahl.
public class IconModeAdapter extends ArrayAdapter<CharSequence> implements View.OnClickListener {
    // Die aktuelle Einstellung bei Aufruf der Auswahl.
    private int m_selection;

    // Das Element in den Einstellungen.
    private IconModeSelector m_list;

    // Legt eine neue Listenverwaltung an.
    public IconModeAdapter(Context context, int resourceId, CharSequence[] values, int selected, IconModeSelector selectorList) {
        super(context, resourceId, values);

        m_selection = selected;
        m_list = selectorList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Erst einmal ermitteln wir den Namen, so wir er letztlich auch in den Einstellungen gespeichert ist
        String mode = getItem(position).toString();

        // Den bilden wir dann auf unsere interne Darstellung ab - der Alternativzweig sollte eigentlich überflüssig sein, aber sicher ist sicher
        CalculatorBase.DisplayMode iconMode;
        if (mode.equals(getContext().getResources().getString(R.string.icon_mode_ansi)))
            iconMode = CalculatorBase.DisplayMode.Ansi;
        else if (mode.equals(getContext().getResources().getString(R.string.icon_mode_iec)))
            iconMode = CalculatorBase.DisplayMode.Iec;
        else
            iconMode = CalculatorBase.DisplayMode.Ansi;

        // Nun die visuelle Hilfe zur Auswahl ermitteln - das ODER gibt hier am meisten her
        int icon;
        switch (iconMode) {
            case Ansi:
                icon = R.drawable.or;
                break;
            case Iec:
                icon = R.drawable.or_iec;
                break;
            default:
                // Das brauchen wir eigentlich nur, damit der Compiler nicht herumnörgelt
                icon = R.drawable.or;
        }

        // Zusätzlich natürlich auch noch die textuelle Beschreibung
        String name = getContext().getResources().getStringArray(R.array.pref_icon_mode_entries)[position];

        // Auswahl anlegen
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.icon_mode_selector, parent, false);
        row.setOnClickListener(this);
        row.setTag(mode);

        // Bild setzen
        ImageView iconView = (ImageView) row.findViewById(R.id.icon_mode_icon);
        iconView.setImageResource(icon);

        // Beschreibung setzen
        TextView nameView = (TextView) row.findViewById(R.id.icon_mode_text);
        nameView.setText(name);

        // Und noch die Visualisierung für die aktuelle Auswahl
        RadioButton check = (RadioButton) row.findViewById(R.id.icon_mode_check);
        check.setChecked(position == m_selection);
        check.setClickable(false);

        return row;
    }

    @Override
    public void onClick(View row) {
        // Der Wert für die Einstellung wird einfach durchgereicht
        m_list.setResult((String) row.getTag());
    }
}