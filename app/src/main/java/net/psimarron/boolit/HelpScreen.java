package net.psimarron.boolit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HelpScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help_screen);

        LinearLayout calculators = (LinearLayout) findViewById(R.id.calculator_list);

        for (int i = 0; i < Calculator.getCalculatorCount(); i++) {
            Calculator calculator = Calculator.createCalculator(i);

            getLayoutInflater().inflate(R.layout.help_image, calculators, true);

            ImageView image = (ImageView) calculators.getChildAt(i).findViewById(R.id.inner_image);
            image.setImageResource(calculator.getImageResourceId());
            image.setTag(new Integer(i));

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CalculatorInfo.show(HelpScreen.this, (Integer) v.getTag());
                }
            });
        }
    }
}
