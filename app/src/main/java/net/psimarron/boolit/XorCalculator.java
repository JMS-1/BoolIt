package net.psimarron.boolit;

public class XorCalculator extends CalculatorBase {
    public XorCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) != getInput(1);
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.xor;
    }

    @Override
    public int getName() {
        return R.string.short_xor;
    }

    @Override
    public int getDescription() {
        return R.string.long_xor;
    }
}
