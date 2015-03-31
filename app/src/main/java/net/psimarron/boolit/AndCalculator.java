package net.psimarron.boolit;

public class AndCalculator extends Calculator {
    public AndCalculator() {
        super(2, R.drawable.and);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) && getInput(1);
    }
}
