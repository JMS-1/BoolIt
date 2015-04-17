package net.psimarron.boolit;

// Repräsentiert ein NOT Gatter.
public class NotCalculator extends CalculatorBase {
    public NotCalculator() {
        super(1);
    }

    @Override
    public boolean getOutput() {
        return !getInput(0);
    }

    @Override
    public int getImageResourceId(DisplayMode mode) {

        switch (mode) {
            default:
                // Der Vorzugsmodus ist US ANSI 91-1984
                return R.drawable.not;
        }
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
