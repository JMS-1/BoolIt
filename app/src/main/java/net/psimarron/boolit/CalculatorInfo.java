package net.psimarron.boolit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CalculatorInfo extends Activity {
    private static final String EXTRA_CALCULATOR = "index";

    private CalculatorBase m_calculator;

    public static void show(Activity parent, int index) {
        Intent intent = new Intent();
        intent.setClass(parent, CalculatorInfo.class);
        intent.putExtra(CalculatorInfo.EXTRA_CALCULATOR, index);
        parent.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        m_calculator = CalculatorBase.createCalculator(getIntent().getIntExtra(EXTRA_CALCULATOR, -1));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator_info);

        ImageView image = (ImageView) findViewById(R.id.info_picture);
        TextView description = (TextView) findViewById(R.id.info_description);

        setTitle(m_calculator.getName());
        description.setText(m_calculator.getDescription());
        image.setImageResource(m_calculator.getImageResourceId());
    }
}
