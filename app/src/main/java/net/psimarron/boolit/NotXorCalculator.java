package net.psimarron.boolit;

public class NotXorCalculator extends Calculator {
    public NotXorCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) == getInput(1);
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.xnor;
    }

    @Override
    public int getShortNameId() {
        return R.string.short_xnor;
    }
}
