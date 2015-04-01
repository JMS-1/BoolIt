package net.psimarron.boolit;

public class OrCalculator extends Calculator {
    public OrCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) || getInput(1);
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.or;
    }

    @Override
    public int getShortNameId() {
        return R.string.short_or;
    }
}
