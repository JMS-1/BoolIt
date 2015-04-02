package net.psimarron.boolit;

public abstract class CalculatorBase {
    private final static Class<?>[] _CalculatorClasses =
            {
                    AndCalculator.class,
                    OrCalculator.class,
                    NotAndCalculator.class,
                    NotOrCalculator.class,
                    XorCalculator.class,
                    NotXorCalculator.class,
                    NotCalculator.class,
            };
    private final boolean[] m_inputs;
    private int m_index;

    protected CalculatorBase(int numberOfInputs) {
        m_inputs = new boolean[numberOfInputs];
    }

    public static int getCalculatorCount() {
        return _CalculatorClasses.length;
    }

    public static CalculatorBase createCalculator(int index) {
        try {
            CalculatorBase calculator = (CalculatorBase) _CalculatorClasses[index].newInstance();

            calculator.m_index = index;

            return calculator;
        } catch (InstantiationException e) {
            throw new IndexOutOfBoundsException("createCalculator");
        } catch (IllegalAccessException e) {
            throw new IndexOutOfBoundsException("createCalculator");
        }
    }

    public int getIndex() {
        return m_index;
    }

    public int getCount() {
        return m_inputs.length;
    }

    public boolean getInput(int index) {
        return m_inputs[index];
    }

    public void setInput(int index, boolean state) {
        m_inputs[index] = state;
    }

    public abstract boolean getOutput();

    public abstract int getImageResourceId();

    public abstract int getName();

    public abstract int getDescription();
}
