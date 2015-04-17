package net.psimarron.boolit;

// Repräsentiert ein NAND Gatter.
public class NotAndCalculator extends CalculatorBase {
    public NotAndCalculator() {
        super(2);
    }

    @Override
    public boolean getOutput() {
        return !(getInput(0) && getInput(1));
    }

    @Override
    public int getImageResourceId(DisplayMode mode) {

        switch (mode) {
            case Iec:
                // IEC 60617-12
                return R.drawable.nand_iec;
            default:
                // Der Vorzugsmodus ist US ANSI 91-1984
                return R.drawable.nand;
        }
    }

    @Override
    public int getName() {
        return R.string.short_nand;
    }

    @Override
    public int getDescription() {
        return R.string.long_nand;
    }
}
