package net.psimarron.boolit;

public class XorCalculator extends Calculator {
    public XorCalculator() {
        super(2, R.drawable.xor);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) != getInput(1);
    }
}
