package net.psimarron.boolit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

// Diese Aktivität zeigt eine kurze Beschreibung eines Gatters an.
public class CalculatorInfo extends Activity {
    // Die Identifikation der laufenden Nummer des Gatters im Intent.
    private static final String EXTRA_CALCULATOR = "index";

    // Zeigt die Beschreibung eines Gatters an.
    public static void show(Activity parent, int index) {
        Intent intent = new Intent();
        intent.setClass(parent, CalculatorInfo.class);
        intent.putExtra(CalculatorInfo.EXTRA_CALCULATOR, index);
        parent.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Visuelle Elemente laden
        setContentView(R.layout.activity_calculator_info);

        // Relevante Elemente identifizieren
        ImageView image = (ImageView) findViewById(R.id.info_picture);
        TextView description = (TextView) findViewById(R.id.info_description);

        // Gewünschtes Gatter erzeugen
        CalculatorBase calculator = CalculatorBase.createCalculator(getIntent().getIntExtra(EXTRA_CALCULATOR, -1));

        // Informationen über das Gatter in die Oberfläche übertragen
        setTitle(calculator.getName());
        description.setText(calculator.getDescription());
        image.setImageResource(calculator.getImageResourceId());
    }
}
