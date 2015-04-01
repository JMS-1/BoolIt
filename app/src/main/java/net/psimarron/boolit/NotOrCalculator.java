package net.psimarron.boolit;

public class NotOrCalculator extends Calculator {
    public NotOrCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return !(getInput(0) || getInput(1));
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.nor;
    }

    @Override
    public int getShortNameId() {
        return R.string.short_nor;
    }
}
