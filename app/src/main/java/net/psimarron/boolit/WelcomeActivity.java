package net.psimarron.boolit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;


public class WelcomeActivity extends Activity implements View.OnClickListener {
    private final Random m_generator = new Random();

    private OneGuess m_current;

    private ImageView m_calculator;

    private ImageView m_input0;

    private ImageView m_input1;

    private ImageView m_guessOn;

    private ImageView m_guessOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        m_calculator = (ImageView) findViewById(R.id.calculator);
        m_input0 = (ImageView) findViewById(R.id.input_guess);
        m_input1 = (ImageView) findViewById(R.id.input_random);
        m_guessOn = (ImageView) findViewById(R.id.output_on);
        m_guessOff = (ImageView) findViewById(R.id.output_off);

        m_guessOn.setOnClickListener(this);
        m_guessOff.setOnClickListener(this);

        reset(false);
    }

    private void reset(boolean lastGuess) {
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

        if (guessOn == m_current.Calculator.getOutput())
            reset(guessOn);
    }
}
