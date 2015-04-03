package net.psimarron.boolit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

// Diese Aktivität ist das eigentliche Spiel. Sie wird auch als EInstieg angezeigt.
public class WelcomeActivity extends Activity implements View.OnClickListener {
    // Der Name der persistenten Ablage der bisher durchgeführten Spiele.
    private final String NAME_TRIES = "tries";

    // Der Name der persistenten Ablage der bisher gesammelten Punkte.
    private final String NAME_POINTS = "points";

    // Das aktuell angezeigte Gatter.
    private CalculatorBase m_current;

    // Das Bild des aktuell angezeigten Gatters.
    private ImageView m_calculator;

    // Der erste Eingang des aktuell angezeigten Gatters - ausgeblendet für Gatter mit einem Eingang.
    private ImageView m_input0;

    // Der zweite Eingang des aktuell angezeigten Gatters - ausgeblendet für Gatter mit einem Eingang.
    private ImageView m_input1;

    //  Der einzige Eingang des aktuell angezeigten Gatters - ausgeblendet für Gatter mit zwei Eingängen.
    private ImageView m_input01;

    // Die Auswahl für einen gesetzten Ausgang.
    private ImageView m_guessOn;

    // Die Auswahl für einen nicht gesetzten Ausgang.
    private ImageView m_guessOff;

    // Die Anzeige des Spielstands.
    private TextView m_points;

    // Die gesamte Anzahl der bisher gesammelten Punkte.
    private long m_collected;

    // Die Anzahl der bisher durchgeführten Spiele.
    private long m_tries;

    // Der Zeitpunkt, an de, das aktuelle Spiel begonnen wurde.
    private Date m_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Aktuellen Spielstand laden
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        m_tries = preferences.getLong(NAME_TRIES, 0);
        m_collected = preferences.getLong(NAME_POINTS, 0);

        // Oberflächenelemente erzeugen
        setContentView(R.layout.activity_welcome);

        // Relevante Oberflächenelemente ermitteln
        m_calculator = (ImageView) findViewById(R.id.calculator);
        m_input0 = (ImageView) findViewById(R.id.input_guess);
        m_input1 = (ImageView) findViewById(R.id.input_random_2);
        m_input01 = (ImageView) findViewById(R.id.input_random_1);
        m_guessOn = (ImageView) findViewById(R.id.output_on);
        m_guessOff = (ImageView) findViewById(R.id.output_off);
        m_points = (TextView) findViewById(R.id.points);

        // Auswahl des Spielers überwachen
        m_guessOn.setOnClickListener(this);
        m_guessOff.setOnClickListener(this);

        // Beim Berühren des angezeigten Gatters werden dessen Detailinformationen angezeigt
        m_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorInfo.show(WelcomeActivity.this, m_current.getIndex());
            }
        });

        // Erstes Spiel beginnen
        newChallenge();
    }

    // Die erste Auswahl des Spielers bearbeiten.
    private void guess(boolean correct) {
        // Reaktionszeit ermitteln
        Date end = new Date();
        long delta = end.getTime() - m_start.getTime();

        // Anzahl der Spiele zählen
        m_tries++;

        // Bei einer richtigen Antwort gibt es Punkte zwischen 1 (10 Sekunden oder mehr) und 100 (1 Sekunde oder weniger)
        if (correct)
            if (delta <= 1000)
                m_collected += 100;
            else if (delta >= 10000)
                m_collected += 1;
            else
                m_collected += Math.round(1 + (99 * (10000 - delta) / 9000));

        // Spielstand vermerken
        updatePreferences();
    }

    // Vermerkt den aktuellen Spielstand.
    private void updatePreferences() {
        SharedPreferences.Editor preferences = getPreferences(Context.MODE_PRIVATE).edit();
        preferences.putLong(NAME_TRIES, m_tries);
        preferences.putLong(NAME_POINTS, m_collected);
        preferences.commit();
    }

    // Löscht den aktuellen Spielstand.
    private void reset() {
        m_collected = 0;
        m_tries = 0;

        updatePreferences();

        // Neues Spiel beginnen
        newChallenge();
    }

    // Beginnt ein neues Spiel.
    private void newChallenge() {
        // Erst einmal den aktuellen Spielstand anzeigen.
        if (m_tries < 1)
            m_points.setText(null);
        else
            m_points.setText(getResources().getString(R.string.result_current, Math.round(m_collected / m_tries)));

        Random generator = new Random();

        // Zufällig ein Gatter auswählen
        CalculatorBase calculator = CalculatorBase.createCalculator(generator.nextInt(CalculatorBase.getCalculatorCount()));

        // Zufällig die Eingänge belegen
        for (int i = 0; i < calculator.getCount(); i++)
            calculator.setInput(i, generator.nextBoolean());

        // Und die Anzeige entsprechend aufbereiten
        changeGuess(calculator);
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
                // Spielstand zurücksetzen
                reset();
                return true;
            case R.id.action_help:
                // Erläuterungen zum Spiel anzeigen
                Intent intent = new Intent();
                intent.setClass(this, HelpScreen.class);
                startActivity(intent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Ein neues Spiel aktivieren und in der Oberfläche anzeigen.
    private void changeGuess(CalculatorBase calculator) {
        // Startzeitpunkt und Spiel merken
        m_current = calculator;
        m_start = new Date();

        // Gatter einzeigen
        m_calculator.setImageResource(calculator.getImageResourceId());

        // Den oder die Eingänge anzeigen
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

        // Die Anzeige der Auswahl des Ausgangs auf den Anfangszustand bringen
        m_guessOn.setActivated(false);
        m_guessOff.setActivated(false);
    }

    @Override
    public void onClick(View v) {
        // Prüfen, was für eine Auswahl der Spieler getroffen hat und ob diese korrekt ist
        boolean guessOn = (v == m_guessOn);
        boolean win = (guessOn == m_current.getOutput());

        // Spielstand nur beim ersten Versuch aktualisieren
        boolean alreadyLost = m_guessOn.isActivated() || m_guessOff.isActivated();
        if (!alreadyLost)
            guess(win);

        // Bei einer richtigen Antwort wird direkt ein neues Spiel gestartet, eine falsche Antwort entsprechend visualisiert
        if (win)
            newChallenge();
        else if (!alreadyLost)
            v.setActivated(true);
    }
}
