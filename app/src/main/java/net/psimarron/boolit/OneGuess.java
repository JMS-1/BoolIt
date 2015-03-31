package net.psimarron.boolit;

import java.util.Random;

public class OneGuess {
    public final Calculator Calculator;
    private final Random m_generator = new Random();

    public OneGuess(Calculator calculator, boolean lastGuess) {
        Calculator = calculator;
        Calculator.setInput(0, lastGuess);

        for (int i = 1; i < Calculator.getCount(); i++)
            Calculator.setInput(i, m_generator.nextBoolean());
    }
}
