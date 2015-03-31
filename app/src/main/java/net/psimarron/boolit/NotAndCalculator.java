package net.psimarron.boolit;

public class NotAndCalculator extends Calculator {
    public NotAndCalculator() {
        super(2, R.drawable.nand);
    }

    @Override
    public boolean getOutput() {
        return !(getInput(0) && getInput(1));
    }
}
