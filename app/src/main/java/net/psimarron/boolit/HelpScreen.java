package net.psimarron.boolit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

// Diese Aktivität gibt eine kurze Eingührung in die Idee des Spiels.
public class HelpScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Oberflächenelemente laden
        setContentView(R.layout.activity_help_screen);

        // Die noch leere Liste der mögichen Gatter laden
        LinearLayout calculators = (LinearLayout) findViewById(R.id.calculator_list);

        // Alle möglichen Gatter in der Oberfläche anzeigen
        for (int i = 0; i < CalculatorBase.getCalculatorCount(); i++) {
            // Gatter erzeugen
            CalculatorBase calculator = CalculatorBase.createCalculator(i);

            // Oberflächenelement für die Anzeige des zugehörigen Bildes erzeugen
            getLayoutInflater().inflate(R.layout.help_image, calculators, true);

            // Bild eintragen und mit der laufenden Nummer der Art des Gatters versehen
            ImageView image = (ImageView) calculators.getChildAt(i).findViewById(R.id.inner_image);
            image.setImageResource(calculator.getImageResourceId(WelcomeActivity.getIconMode(this)));
            image.setTag(new Integer(i));

            // Beim Berüchrung des Bildes werden dann die Detailinformationen zum Gatter angezeigt
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CalculatorInfo.show(HelpScreen.this, (Integer) v.getTag());
                }
            });
        }
    }
}
