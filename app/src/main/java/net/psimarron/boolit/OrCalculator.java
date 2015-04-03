package net.psimarron.boolit;

// Repräsentiert ein OR Gatter.
public class OrCalculator extends CalculatorBase {
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
    public int getName() {
        return R.string.short_or;
    }

    @Override
    public int getDescription() {
        return R.string.long_or;
    }
}
