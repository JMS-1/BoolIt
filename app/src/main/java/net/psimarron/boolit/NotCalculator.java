package net.psimarron.boolit;

public class NotCalculator extends CalculatorBase {
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
    public int getName() {
        return R.string.short_not;
    }

    @Override
    public int getDescription() {
        return R.string.long_not;
    }
}
