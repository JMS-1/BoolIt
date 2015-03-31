package net.psimarron.boolit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class WelcomeActivity extends Activity implements View.OnClickListener {
    private final Random m_generator = new Random();

    private OneGuess m_current;

    private ImageView m_calculator;

    private ImageView m_input0;

    private ImageView m_input1;

    private ImageView m_guessOn;

    private ImageView m_guessOff;

    private TextView m_points;

    private int m_correct;

    private int m_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        m_calculator = (ImageView) findViewById(R.id.calculator);
        m_input0 = (ImageView) findViewById(R.id.input_guess);
        m_input1 = (ImageView) findViewById(R.id.input_random);
        m_guessOn = (ImageView) findViewById(R.id.output_on);
        m_guessOff = (ImageView) findViewById(R.id.output_off);
        m_points = (TextView) findViewById(R.id.points);

        m_guessOn.setOnClickListener(this);
        m_guessOff.setOnClickListener(this);

        reset(false);
    }

    private int getTotal() {
        return m_total;
    }

    private int getCorrect() {
        return m_correct;
    }

    private void guess(boolean correct) {
        m_total++;

        if (correct)
            m_correct++;
    }

    private void reset() {
        m_correct = 0;
        m_total = 0;

        reset(false);
    }

    private void reset(boolean lastGuess) {
        if (getTotal() == 0)
            m_points.setText(R.string.result_initial);
        else
            m_points.setText(getResources().getString(R.string.result_current, (int) Math.round(100d * getCorrect() / getTotal())));

        Calculator calculator;

        switch (m_generator.nextInt(7)) {
            case 0:
                calculator = new AndCalculator();
                break;
            case 1:
                calculator = new OrCalculator();
                break;
            case 2:
                calculator = new XorCalculator();
                break;
            case 3:
                calculator = new NotAndCalculator();
                break;
            case 4:
                calculator = new NotOrCalculator();
                break;
            case 5:
                calculator = new NotXorCalculator();
                break;
            case 6:
                calculator = new NotCalculator();
                break;
            default:
                return;
        }

        changeGuess(new OneGuess(calculator, lastGuess));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                reset();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeGuess(OneGuess newGuess) {
        m_current = newGuess;

        Calculator calculator = newGuess.Calculator;

        m_calculator.setImageResource(calculator.ImageResourceId);
        m_input0.setActivated(calculator.getInput(0));
        m_input1.setVisibility((calculator.getCount() > 1) ? View.VISIBLE : View.INVISIBLE);

        if (calculator.getCount() > 1)
            m_input1.setActivated(calculator.getInput(1));
    }

    @Override
    public void onClick(View v) {
        boolean guessOn = (v == m_guessOn);

        guess(guessOn == m_current.Calculator.getOutput());

        reset(guessOn);
    }
}
