package net.psimarron.boolit;

// Repräsentiert ein NOT XOR Gatter.
public class NotXorCalculator extends CalculatorBase {
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
    public int getName() {
        return R.string.short_xnor;
    }

    @Override
    public int getDescription() {
        return R.string.long_xnor;
    }
}
