package net.psimarron.boolit;

public class AndCalculator extends CalculatorBase {
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
    public int getName() {
        return R.string.short_and;
    }

    @Override
    public int getDescription() {
        return R.string.long_and;
    }
}
