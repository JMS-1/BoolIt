package net.psimarron.boolit;

public class NotXorCalculator extends Calculator {
    public NotXorCalculator() {
        super(2, R.drawable.xnor);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) == getInput(1);
    }
}
