package net.psimarron.boolit;

public class XorCalculator extends Calculator {
    public XorCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) != getInput(1);
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.xor;
    }

    @Override
    public int getShortNameId() {
        return R.string.short_xor;
    }
}
