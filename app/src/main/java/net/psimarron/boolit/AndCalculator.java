package net.psimarron.boolit;

public class AndCalculator extends Calculator {
    public AndCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) && getInput(1);
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.and;
    }

    @Override
    public int getShortNameId() {
        return R.string.short_and;
    }
}
