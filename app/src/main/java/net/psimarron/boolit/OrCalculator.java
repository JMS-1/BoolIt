package net.psimarron.boolit;

public class OrCalculator extends Calculator {
    public OrCalculator() {
        super(2, R.drawable.or);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) || getInput(1);
    }
}
