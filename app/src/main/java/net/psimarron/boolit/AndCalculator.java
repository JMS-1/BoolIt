package net.psimarron.boolit;

// Repräsentiert ein AND Gatter.
public class AndCalculator extends CalculatorBase {
    public AndCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return getInput(0) && getInput(1);
    }

    @Override
    public int getImageResourceId(DisplayMode mode) {
        switch (mode) {
            case Iec:
                // IEC 60617-12
                return R.drawable.and_iec;
            default:
                // Der Vorzugsmodus ist US ANSI 91-1984
                return R.drawable.and;
        }
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
