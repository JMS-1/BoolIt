package net.psimarron.boolit;

public class NotAndCalculator extends Calculator {
    public NotAndCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return !(getInput(0) && getInput(1));
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.nand;
    }

    @Override
    public int getShortNameId() {
        return R.string.short_nand;
    }
}
