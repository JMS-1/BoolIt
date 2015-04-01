package net.psimarron.boolit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class CalculatorInfo extends Activity {
    public static final String EXTRA_CALCULATOR = "index";

    private Calculator m_calculator;

    private ImageView m_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        m_calculator = Calculator.createCalculator(getIntent().getIntExtra(EXTRA_CALCULATOR, -1));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator_info);

        m_image = (ImageView) findViewById(R.id.info_picture);

        setTitle(m_calculator.getShortNameId());
        m_image.setImageResource(m_calculator.getImageResourceId());
    }
}
