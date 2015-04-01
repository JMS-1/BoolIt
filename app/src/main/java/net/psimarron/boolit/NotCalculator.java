package net.psimarron.boolit;

public class NotCalculator extends Calculator {
    public NotCalculator() {
        super(1);
    }

    @Override
    public boolean getOutput() {
        return !getInput(0);
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.not;
    }

    @Override
    public int getShortNameId() {
        return R.string.short_not;
    }
}
