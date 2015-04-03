package net.psimarron.boolit;

// Repräsentiert ein NOR Gatter.
public class NotOrCalculator extends CalculatorBase {
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
    public int getName() {
        return R.string.short_nor;
    }

    @Override
    public int getDescription() {
        return R.string.long_nor;
    }
}
