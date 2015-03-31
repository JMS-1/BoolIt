package net.psimarron.boolit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;


public class WelcomeActivity extends Activity implements View.OnClickListener {
    private final Random m_generator = new Random();

    private OneGuess m_current;

    private ImageView m_calculator;

    private ImageView m_input0;

    private ImageView m_input1;

    private ImageView m_input01;

    private ImageView m_guessOn;

    private ImageView m_guessOff;

    private TextView m_points;

    private long m_collected;

    private long m_tries;

    private Date m_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        m_calculator = (ImageView) findViewById(R.id.calculator);
        m_input0 = (ImageView) findViewById(R.id.input_guess);
        m_input1 = (ImageView) findViewById(R.id.input_random_2);
        m_input01 = (ImageView) findViewById(R.id.input_random_1);
        m_guessOn = (ImageView) findViewById(R.id.output_on);
        m_guessOff = (ImageView) findViewById(R.id.output_off);
        m_points = (TextView) findViewById(R.id.points);

        m_guessOn.setOnClickListener(this);
        m_guessOff.setOnClickListener(this);

        reset(false);
    }

    private void guess(boolean correct) {
        Date end = new Date();
        long delta = end.getTime() - m_start.getTime();

        m_tries++;

        if (!correct)
            return;

        if (delta <= 1000)
            m_collected += 100;
        else if (delta >= 10000)
            m_collected += 1;
        else
            m_collected += Math.round(1 + (99 * (10000 - delta) / 9000));
    }

    private void reset() {
        m_collected = 0;
        m_tries = 0;

        reset(false);
    }

    private void reset(boolean lastGuess) {
        if (m_tries < 1)
            m_points.setText(null);
        else
            m_points.setText(getResources().getString(R.string.result_current, Math.round(m_collected / m_tries)));

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
        m_start = new Date();

        Calculator calculator = newGuess.Calculator;

        m_calculator.setImageResource(calculator.ImageResourceId);

        if (calculator.getCount() > 1) {
            m_input0.setActivated(calculator.getInput(0));
            m_input0.setVisibility(View.VISIBLE);
            m_input1.setActivated(calculator.getInput(1));
            m_input1.setVisibility(View.VISIBLE);
            m_input01.setVisibility(View.INVISIBLE);
        } else {
            m_input0.setVisibility(View.INVISIBLE);
            m_input1.setVisibility(View.INVISIBLE);
            m_input01.setVisibility(View.VISIBLE);
            m_input01.setActivated(calculator.getInput(0));
        }
    }

    @Override
    public void onClick(View v) {
        boolean guessOn = (v == m_guessOn);

        guess(guessOn == m_current.Calculator.getOutput());

        reset(guessOn);
    }
}
