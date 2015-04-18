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

public class IconModeAdapter extends ArrayAdapter<CharSequence> implements View.OnClickListener {
    private int m_selection;

    private IconModeSelector m_list;

    public IconModeAdapter(Context context, int resourceId, CharSequence[] values, int selected, IconModeSelector selectorList) {
        super(context, resourceId, values);

        m_selection = selected;
        m_list = selectorList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the current selection name
        String mode = getItem(position).toString();

        // Map to mode
        CalculatorBase.DisplayMode iconMode;
        if (mode.equals("ANSI"))
            iconMode = CalculatorBase.DisplayMode.Ansi;
        else if (mode.equals("IEC"))
            iconMode = CalculatorBase.DisplayMode.Iec;
        else
            iconMode = CalculatorBase.DisplayMode.Ansi;

        // Find the related icon
        int icon;
        switch (iconMode) {
            case Ansi:
                icon = R.drawable.or;
                break;
            case Iec:
                icon = R.drawable.or_iec;
                break;
            default:
                icon = R.drawable.or;
        }

        // Find the related text
        String name = getContext().getResources().getStringArray(R.array.pref_icon_mode_entries)[position];

        // Create the new wor
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();

        View row = inflater.inflate(R.layout.icon_mode_selector, parent, false);
        row.setOnClickListener(this);
        row.setTag(mode);

        // Set icon
        ImageView iconView = (ImageView) row.findViewById(R.id.icon_mode_icon);
        iconView.setImageResource(icon);

        // Set text
        TextView nameView = (TextView) row.findViewById(R.id.icon_mode_text);
        nameView.setText(name);

        // Set selection
        RadioButton check = (RadioButton) row.findViewById(R.id.icon_mode_check);
        check.setChecked(position == m_selection);
        check.setClickable(false);

        return row;
    }

    @Override
    public void onClick(View row) {
        m_list.setResult((String) row.getTag());
    }
}