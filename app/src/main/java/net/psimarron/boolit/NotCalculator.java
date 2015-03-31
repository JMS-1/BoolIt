package net.psimarron.boolit;

public class NotCalculator extends Calculator {
    public NotCalculator() {
        super(1, R.drawable.not);
    }

    @Override
    public boolean getOutput() {
        return !getInput(0);
    }
}
