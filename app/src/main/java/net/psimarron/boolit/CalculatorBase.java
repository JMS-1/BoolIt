package net.psimarron.boolit;

// Eine Basisklasse für in diesem Spiel benutzte Logikgatter.
public abstract class CalculatorBase {
    // Alle Arten von Gattern, die verwendet werden können.
    private final static Class<?>[] _CalculatorClasses =
            {
                    AndCalculator.class,
                    OrCalculator.class,
                    NotAndCalculator.class,
                    NotOrCalculator.class,
                    XorCalculator.class,
                    NotXorCalculator.class,
                    NotCalculator.class,
            };

    // Die Eingänge des Gatters.
    private final boolean[] m_inputs;

    // Die interne und willkürliche laufende Nummer der Art des Gatters - wie in _CalculatorClasses eingeordnet.
    private int m_index;

    // Initialisiert ein Gatter.
    protected CalculatorBase(int numberOfInputs) {
        m_inputs = new boolean[numberOfInputs];
    }

    // Meldet die Anzahl der unterschiedlichen Gatterarten.
    public static int getCalculatorCount() {
        return _CalculatorClasses.length;
    }

    // Erzeugt ein bestimmtes Gatter.
    public static CalculatorBase createCalculator(int index) {
        try {
            // Dynamisch per Klassenbeschreibung erzeugen
            CalculatorBase calculator = (CalculatorBase) _CalculatorClasses[index].newInstance();

            // Den Index für den Aufruf der Hilfeseite merken
            calculator.m_index = index;

            return calculator;
        } catch (InstantiationException e) {
            throw new IndexOutOfBoundsException("createCalculator");
        } catch (IllegalAccessException e) {
            throw new IndexOutOfBoundsException("createCalculator");
        }
    }

    // Meldet die interne laufende Nummer der Art des Gatters.
    public int getIndex() {
        return m_index;
    }

    // Meldet die Anzahl der Eingänge.
    public int getCount() {
        return m_inputs.length;
    }

    // Meldet den Zustand eines Eingangs.
    public boolean getInput(int index) {
        return m_inputs[index];
    }

    // Verändert den Zustand eines Eingangs.
    public void setInput(int index, boolean state) {
        m_inputs[index] = state;
    }

    // Meldet den aktuellen Zustand des Ausgangs.
    public abstract boolean getOutput();

    // Meldet ein Bild des Gatters.
    public abstract int getImageResourceId(DisplayMode mode);

    // Meldet einen Kurznammen für das Gatter.
    public abstract int getName();

    // Meldet eine Beschreibung für das Gatter.
    public abstract int getDescription();

    // Die gewählte Art der Bilder.
    public enum DisplayMode {
        Ansi,

        Iec,
    }
}
