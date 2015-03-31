package net.psimarron.boolit;

public class NotOrCalculator extends Calculator {
    public NotOrCalculator() {
        super(2, R.drawable.nor);
    }

    @Override
    public boolean getOutput() {
        return !(getInput(0) || getInput(1));
    }
}
